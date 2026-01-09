package com.service.charity.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.service.charity.builder.request.ActivityListRequest;
import com.service.charity.builder.request.CheckoutRq;
import com.service.charity.builder.request.EmailDetailsRq;
import com.service.charity.builder.request.ProjectListRequest;
import com.service.charity.config.Constants;
import com.service.charity.model.PaymentSession;
import com.service.charity.model.Project;
import com.service.charity.model.Users;
import com.service.charity.service.AuthService;
import com.service.charity.service.EmailService;
import com.service.charity.service.GoogleRecaptchaService;
import com.service.charity.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/public")
public class PublicController {

	@Autowired
	UserService userService;

	@Autowired
	GoogleRecaptchaService googleRecaptchaService;

	@Autowired
	AuthService authService;

	@Autowired
	EmailService emailService;

//	TEST
//    private static final String ENDPOINT_SECRET = "whsec_YMBsc2t23Xxz51AY5dviFUlp6wzv6AlY";
//    private static final String stripeProductId = "prod_SN1TmxeWcLNrrL";
//    private static final String stripeapikey = "sk_test_51RQCR8R68I1yevpeqgncFrK242495lQJZEFZU3wjTQZm9qIyUDMkR1qA0EQPAfsIXe1YQYo9Bm1fYM6QTLL6Jlle00K6I3A2e9";

// PROD
    private static final String ENDPOINT_SECRET = "whsec_iomdT8iZ8NJ1MSiqYsFOGlvQcPJeqQ4P";
    private static final String stripeProductId = "prod_TlFjt00bwvLQ79";
    private static final String stripeapikey = "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";

    
	@RequestMapping(value = { "/project/list", "/{version}/project/list" }, method = RequestMethod.POST)
	public ResponseEntity<?> projectlist(@RequestBody ProjectListRequest request,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			@PathVariable(name = "version", required = false) String version) {

		return userService.projectlist(locale, request);
	}

	@RequestMapping(value = { "/project/details/{id}", "/{version}/project/details/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<?> projectdetails(@PathVariable Long id,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return userService.projectdetails(locale, id);
	}

	@PostMapping("/checkout/session")
	public ResponseEntity<?> createCheckoutSession(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			@RequestHeader(name = "token", required = false) String token, 
			@RequestHeader(name = "apikey", required = false) String apikey, 
			@RequestHeader(name = "apisecret", required = false) String apisecret, 
			@RequestHeader(name = "username", required = false) String username, 
			@RequestBody CheckoutRq rq) throws StripeException {

		// Validate token and captcha if needed
//		ResponseEntity<?> verifycaptcha = googleRecaptchaService.verifyRecaptcha(locale, rq.getCaptchaToken());
//		if (verifycaptcha != null) 
//			return verifycaptcha;

		boolean isregisteruser = rq.getUsername().equals(Constants.anonymous) ? false : true;
		if (token != null) {
			ResponseEntity<?> auth = authService.callAuth(apikey, apisecret, username, token, Constants.DONOTCHECKME, locale.getLanguage());
	
			if (auth != null && auth.getBody() instanceof Users) { // success
				isregisteruser = false;
				Users user = (Users) auth.getBody();
		        rq.setUsername(user.getUsername());
			}
			else {
				// invalid token
				return auth;
			}
		}
		
		Stripe.apiKey = stripeapikey; 
		
		Project project = userService.getProject(rq.getProjectid());
        if (project == null) 
            return ResponseEntity.status(400).body("Invalid request");

        String currency = project.getCurrency() != null ? project.getCurrency() : Constants.CURRENCY;
		long amountInCents = rq.getAmount().multiply(BigDecimal.valueOf(100)).longValue();
		List<SessionCreateParams.LineItem> lineItems = List.of(
		    SessionCreateParams.LineItem.builder()
		        .setQuantity(1L)
		        .setPriceData(
		            SessionCreateParams.LineItem.PriceData.builder()
		                .setCurrency(currency)
		                .setUnitAmount(amountInCents) // (in fils)
		                .setProduct(stripeProductId) // Use the Product ID here
		                .build()
		        )
		        .build()
		);
		
		PaymentSession ps = userService.handlepaymentsession(token, rq, isregisteruser);

		SessionCreateParams params = SessionCreateParams.builder().addAllLineItem(lineItems)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("https://internationalmissiondevie.org/successpayment?session_id={CHECKOUT_SESSION_ID}&ps=" + ps.getId())
				.setCancelUrl("https://internationalmissiondevie.org/cancelpayment")
				.putMetadata(Constants.PSID, ps.getId())
				.setCustomerCreation(SessionCreateParams.CustomerCreation.ALWAYS)
				.setClientReferenceId(ps.getId())
				.build();

		System.out.println("Creating Stripe session with psid = " + ps.getId());

		Session session = Session.create(params);

		Map<String, Object> response = new HashMap<>();
		response.put("checkoutUrl", session.getUrl());

		return ResponseEntity.ok(response);
	}

	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebhook(HttpServletRequest request) throws IOException {
		String payload;
        String sigHeader = request.getHeader("Stripe-Signature");

        try {
            payload = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return ResponseEntity.status(400).body("Failed to read payload");
        }

        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, ENDPOINT_SECRET);
        } catch (SignatureVerificationException e) {
            System.out.println("Webhook signature verification failed: " + e.getMessage());
            return ResponseEntity.status(400).body("Invalid signature");
        }

        // Log raw payload
        System.out.println(">> Webhook received:");
        System.out.println(payload);

        try {
            // Parse JSON manually using JSONObject
            JSONObject json = new JSONObject(payload);
            String eventType = json.getString("type");
            
        	PaymentSession ps = userService.handlepaymentwebhook(json, eventType);
        	
        } catch (Exception e) {
            System.out.println("Error parsing event JSON: " + e.getMessage());
            return ResponseEntity.status(400).body("Invalid event structure");
        }

        return ResponseEntity.ok("Webhook received");
	}

	@RequestMapping(value = "/project/download/file/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadfile(HttpServletRequest request, @PathVariable String fileName) {

		return userService.downloadfile(fileName);
	}

	@RequestMapping(value = "/project/file/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<?> returnbase64file(HttpServletRequest request, @PathVariable String fileName) {

		return userService.returnbase64file(fileName);
	}
	
	

	private final String secret = "YOUR_VERIFONE_SECRET_KEY";
	private final String keyId = "YOUR_KEY_ID";
	private final String merchantAccountId = "YOUR_ACCOUNT_ID"; // from Verifone dashboard
	private final String successRedirect = "https://yourdomain.com/thank-you";

	@PostMapping("/2checkout/create")
	public ResponseEntity<Map<String, Object>> createDonation(@RequestParam long amountCents) {
		try {
			String token = generateCheckoutToken(amountCents);
			String redirectUrl = "https://secure.snd.verifone.cloud/hpp/token/" + token;

			Map<String, Object> response = new HashMap<>();
			response.put("checkoutUrl", redirectUrl);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
		}
	}

	private String generateCheckoutToken(long amountCents) {
		Algorithm algorithm = Algorithm.HMAC256(secret);

		Instant now = Instant.now();
		Instant exp = now.plusSeconds(300); // token valid for 5 min

		String merchantRef = UUID.randomUUID().toString();

		Map<String, Object> payment = Map.of("amount", amountCents, "currency", Constants.CURRENCY.toUpperCase(), "reference", merchantRef,
				"returnUrl", successRedirect);

		String token = JWT.create().withIssuer(merchantAccountId).withKeyId(keyId).withIssuedAt(Date.from(now))
				.withExpiresAt(Date.from(exp)).withClaim("payment", payment).sign(algorithm);

		return token;
	}
	
	@PostMapping("/2checkout/webhook")
	public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
	    System.out.println("📨 Received webhook: " + payload);

	    Map<String, Object> payment = (Map<String, Object>) payload.get("payment");
	    if (payment == null) return ResponseEntity.badRequest().body("Missing payment");

	    String status = (String) payment.get("status");
	    String reference = (String) payment.get("reference");
	    Number amount = (Number) payment.get("amount");

	    if ("AUTHORIZED".equalsIgnoreCase(status) || "PAID".equalsIgnoreCase(status)) {
	        // ✅ Store donation
	        System.out.println("Donation successful: " + amount + " cents, ref: " + reference);
	    }

	    return ResponseEntity.ok("Webhook received");
	}
	
	
	/**
	 * Activities
	 */
	@RequestMapping(value = { "/activity/list", "/{version}/activity/list" }, method = RequestMethod.POST)
	public ResponseEntity<?> activitylist(@RequestBody ActivityListRequest request,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			@PathVariable(name = "version", required = false) String version) {

		return userService.activitylist(locale, request);
	}

	@RequestMapping(value = { "/activity/details/{id}", "/{version}/activity/details/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<?> activitydetails(@PathVariable Long id,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return userService.activitydetails(locale, id);
	}

	@RequestMapping(value = "/activity/download/file/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<?> activitydownloadfile(HttpServletRequest request, @PathVariable String fileName) {

		return userService.activitydownloadfile(fileName);
	}
	
	
	
	@RequestMapping(value = {"/email", "/{version}/email"}, 
			method = RequestMethod.POST, headers = "Accept=application/json") 
	public ResponseEntity<?> sendEmail(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			HttpServletRequest request, 
			@PathVariable(name = "version", required = false) String version,
			@RequestBody EmailDetailsRq rq) throws UnsupportedEncodingException { 

		ResponseEntity<?> verifycaptcha = googleRecaptchaService.verifyRecaptcha(locale, rq.getCaptchaToken());
		if (verifycaptcha != null) 
			return verifycaptcha;
		
		rq.setRecipient("missiondevieinternational@gmail.com");	
		boolean sent = emailService.sendSimpleMail(rq);

		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("success", sent);
		
		JSONObject response = new JSONObject(map);
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
}
