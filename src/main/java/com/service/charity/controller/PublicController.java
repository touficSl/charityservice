package com.service.charity.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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
import com.service.charity.builder.request.ProjectListRequest;
import com.service.charity.service.UserService;
import com.stripe.Stripe;
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

	@PostMapping("/create-checkout-session")
	public ResponseEntity<Map<String, Object>> createCheckoutSession() throws StripeException {
		Stripe.apiKey = "sk_test_51RDsi2RofurYzY0n2KhoNXAvbphdtUHQ9LwzyOzEWVjvi5E8pAm7sE2QMutpJMr3k0f9FktaKu2aL7uGubTxaDge00A3e35lz4"; // Replace
																																		// with
																																		// your
																																		// Stripe
																																		// test
																																		// secret
																																		// key

		List<SessionCreateParams.LineItem> lineItems = List.of(SessionCreateParams.LineItem.builder().setQuantity(1L)
				.setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd").setUnitAmount(2000L) // $20.00
						.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
								.setName("Test Product").build())
						.build())
				.build());

		SessionCreateParams params = SessionCreateParams.builder().addAllLineItem(lineItems)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("https://localhost/success?session_id={CHECKOUT_SESSION_ID}")
				.setCancelUrl("https://localhost/cancel").putMetadata("order_id", "12345") // 👈 attach your internal
																							// order ID
				.build();

		Session session = Session.create(params);

		Map<String, Object> response = new HashMap<>();
		response.put("checkoutUrl", session.getUrl());

		return ResponseEntity.ok(response);
	}

	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebhook(HttpServletRequest request) throws IOException {
		String payload = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		String sigHeader = request.getHeader("Stripe-Signature");
		String endpointSecret = "whsec_XXXXXXXXXXXXXXXX"; // Your webhook secret

		Event event;
		try {
			event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("");
		}

		if ("checkout.session.completed".equals(event.getType())) {
			Session session = (Session) event.getDataObjectDeserializer().getObject().get();
			// Fulfill order
			System.out.println("Payment successful for session: " + session.getId());

			String orderId = session.getMetadata().get("order_id");
			return ResponseEntity.ok(orderId);

		}

		return ResponseEntity.ok("");
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

		Map<String, Object> payment = Map.of("amount", amountCents, "currency", "USD", "reference", merchantRef,
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
	        System.out.println("✅ Donation successful: " + amount + " cents, ref: " + reference);
	    }

	    return ResponseEntity.ok("Webhook received");
	}

}
