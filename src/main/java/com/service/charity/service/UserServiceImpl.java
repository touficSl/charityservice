package com.service.charity.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.service.charity.builder.request.CheckoutRq;
import com.service.charity.builder.request.DonateRq;
import com.service.charity.builder.request.EmailDetailsRq;
import com.service.charity.builder.request.ProjectListRequest;
import com.service.charity.builder.response.DatatableResponse;
import com.service.charity.builder.response.MessageResponse;
import com.service.charity.config.Constants;
import com.service.charity.config.SanitizedStringDeserializer;
import com.service.charity.model.Charity;
import com.service.charity.model.PaymentSession;
import com.service.charity.model.Project;
import com.service.charity.model.ProjectImage;
import com.service.charity.model.Users;
import com.service.charity.reposiroty.CharityRepository;
import com.service.charity.reposiroty.PaymentSessionRepository;
import com.service.charity.reposiroty.ProjectImageRepository;
import com.service.charity.reposiroty.ProjectRepository;
import com.service.charity.rest.call.RegisterUser;
import com.service.charity.rest.call.VerifyAuth;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ProjectRepository projectRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CharityRepository charityRepository;
	
	@Autowired
	ProjectImageRepository projectImageRepository;
	
	@Autowired
	PaymentSessionRepository paymentSessionRepository;

	@Autowired
	EmailService emailService;

	@Value("${spring.file.uploaddir}") 
    private String fileuploaddir;


	@Value("${spring.auth.endpoint.api}") 
	private String authendpointapi;
	@Value("${spring.service.auth.register.user.api}") 
	private String authregisteruserapi;
	@Value("${spring.apikey}") 
	private String apikey;
	@Value("${spring.serverkey}") 
	private String serverkey;
	@Value("${spring.apisecret}") 
	private String apisecret;

	@Value("${spring.serverpass}") 
	private String serverpass;
	
	@Override
	public ResponseEntity<?> projectlist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username) {

		try {
			Page<Project> pages = null;
			long totalrows = projectRepository.count();
			long recordsFiltered = totalrows;

			Specification<Project> spec = JPASpecification.returnCharityProjecttSpecification(search, sortcolumn, descending, username);
		    Pageable pageable = PageRequest.of(page, size);
		    pages = projectRepository.findAll(spec, pageable);
		    
			if (search != null && !search.trim().equals("")) {
				List<Project> allusersbysearch = projectRepository.findAll(spec);
				recordsFiltered = allusersbysearch.size();
			} 
	
	        List<Project> list = new ArrayList<Project>(pages.getContent());
	        
	        DatatableResponse<Project> datatableresponse = new DatatableResponse<Project>(draw, totalrows, recordsFiltered, list);
		       
			return ResponseEntity.ok(datatableresponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}

	@Override
	public ResponseEntity<?> donate(Locale locale, Users user, DonateRq rq) {
		try {
			Optional<Project> projectopt = projectRepository.findById(rq.getProjectid());
			if (!projectopt.isPresent()) 
				return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
			
			rq.setUsername(user.getUsername());
			Project project = projectopt.get();
			
			Charity charity = new Charity(rq, project);
			charity = charityRepository.save(charity);
			return ResponseEntity.ok(charity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}

	@Override
	public ResponseEntity<?> projectlist(Locale locale, ProjectListRequest request) {
		try {
	        int page = request.getFromrow() / (request.getTorow() - request.getFromrow());
	        int size = request.getTorow() - request.getFromrow();

	        Pageable pageable = PageRequest.of(page, size, Sort.by("dateTime").descending());

	        Page<Project> projectPage;

	        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
	            projectPage = projectRepository.findByTitleContainingIgnoreCase(request.getTitle(), pageable);
	        } else {
	            projectPage = projectRepository.findAll(pageable);
	        }
	        
	        for (Project project : projectPage.getContent()) {
				List<ProjectImage> images = projectImageRepository.findByProjectId(project.getId());
				List<String> imagelist = new ArrayList<String>();
				if (images != null && images.size() > 0)
					for (ProjectImage pi : images)
						imagelist.add(pi.getPath());
				project.setImages(imagelist);
	        }
			
	        Map<String, Object> response = new HashMap();
	        response.put("projectList", projectPage.getContent());
	        response.put("totalElements", projectPage.getTotalElements());

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
	    }
	}

	@Override
	public ResponseEntity<?> projectdetails(Locale locale, Long id) {
		try {
			Optional<Project> projectopt = projectRepository.findById(id);
			if (!projectopt.isPresent()) 
				return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
			
			Project project = projectopt.get();
			List<ProjectImage> images = projectImageRepository.findByProjectId(project.getId());
			List<String> imagelist = new ArrayList<String>();
			if (images != null && images.size() > 0)
				for (ProjectImage pi : images)
					imagelist.add(pi.getPath());
			project.setImages(imagelist);
			return ResponseEntity.ok(project);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}



	 
	@Override
	public ResponseEntity<?> downloadfile(String fileName) {
		try {
	        Path filePath = Paths.get(fileuploaddir).resolve(fileName).normalize();
	        Resource resource = new UrlResource(filePath.toUri());
	
	        if (!resource.exists()) 
				return ResponseEntity.ok(new MessageResponse("resource_not_found", 111));
	
	        String contentType = getFileContentType(resource);
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse("exception_case", 111));
		}
	}
	private String getFileContentType(Resource resource) {
	    try {
	        // Use Java's built-in file type detection
	        return Files.probeContentType(resource.getFile().toPath());
	    } catch (Exception e) {
	        return "application/octet-stream";
	    }
	}
	 
	@Override
	public ResponseEntity<?> returnbase64file(String fileName) {
		try {
	        Path filePath = Paths.get(fileuploaddir).resolve(fileName).normalize();
	        Resource resource = new UrlResource(filePath.toUri());
	
	        if (!resource.exists()) 
				return ResponseEntity.ok(new MessageResponse("resource_not_found", 111));

	        byte[] fileBytes = Files.readAllBytes(filePath);

	        // Encode the byte array into Base64
	        String base64EncodedFile = Base64.getEncoder().encodeToString(fileBytes);

	        // Get MIME type for the file (e.g., image/jpeg, application/pdf)
	        String mimeType = Files.probeContentType(filePath);

//		        // Return the Base64 encoded string in the response body
//		        return ResponseEntity.ok()
//		                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//		                .contentType(MediaType.parseMediaType(mimeType))
//		                .body("data:" + mimeType + ";base64," + base64EncodedFile);

//				return ResponseEntity.ok(new MessageResponse("data:" + mimeType + ";base64," + base64EncodedFile));
			return ResponseEntity.ok("data:" + mimeType + ";base64," + base64EncodedFile);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse("exception_case", 111));
		}
	}

	@Override
	public PaymentSession handlepaymentsession(String token, CheckoutRq rq, boolean isregisteruser) {

		try {
	        // Create and populate PaymentSession
	        PaymentSession session = new PaymentSession();
	        session.setId(UUID.randomUUID().toString()); // Generate unique ID
	        session.setUsername(rq.getUsername());
	        session.setName(rq.getName());
	        session.setMessage(rq.getMessage());
	        session.setStatus("PENDING"); // Set initial status
	        session.setDatetime(new Date());
	        session.setAmount(rq.getAmount());
	        session.setProjectid(rq.getProjectid());
	        session.setRegisteruser(isregisteruser);
	
	        // Save to database
	        paymentSessionRepository.save(session);
	
	        // Optional: log or trigger payment URL logic
	        System.out.println("Payment session saved: " + session.getId());
	        
	        return session;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaymentSession handlepaymentwebhook(JSONObject payload, String eventType) {
		// save checkout details
		
		boolean success = false;
	    switch (eventType) {
	        case "checkout.session.completed":
	        case "payment_intent.succeeded":
	            success = true;
	            break;
	        default:
	            success = false;
	    }

	    PaymentSession ps = null;

	    JSONObject session = payload.getJSONObject("data").getJSONObject("object");
    	String psid = null;
    	if (session.has("client_reference_id"))
    		psid = session.getString("client_reference_id");
		else if (session.has("metadata")) {
	        JSONObject metadata = session.getJSONObject("metadata");
	        if (metadata.has(Constants.PSID)) 
	            psid = metadata.getString(Constants.PSID); 
        }
		if (psid != null) {
            System.out.println("PaymentIntent succeeded. PS ID: " + psid);

            Optional<PaymentSession> optionalSession = paymentSessionRepository.findById(psid);
            if (optionalSession.isPresent()) {
                ps = optionalSession.get();

                if (session.has("amount_total") && session.has("currency")) {
                    int amountTotal = session.getInt("amount_total");
                    BigDecimal amount = BigDecimal.valueOf(amountTotal).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

                    ps.setStatus(success ? Constants.COMPLETED : Constants.FAILED);
                    ps.setAmount(amount);
                } else {
                    System.out.println("Missing amount_total or currency in session.");
                    ps.setStatus("FAILED");
                }
                
                String errorCode = null;
                String errorMessage = null;
                if (session.has("last_payment_error")) {
                    JSONObject error = session.getJSONObject("last_payment_error");
                    errorCode = error.optString("code");
                    errorMessage = error.optString("message");
                    System.out.println("Payment Error - Code: " + errorCode + ", Message: " + errorMessage);
                    ps.setThirdpartyerror(errorCode + " | " + errorMessage);
                }
                
                // Extract customer email if available
                if (session.has("customer_details")) {
                    JSONObject customerDetails = session.getJSONObject("customer_details");
                    if (customerDetails.has("email")) {
                        String email = customerDetails.getString("email");
                        System.out.println("Customer Email: " + email);
                        ps.setEmail(email); // assuming your PaymentSession has this field
                    }
                }
                
                ps.setThirdpartystatus(eventType);
                ps = paymentSessionRepository.save(ps);
            } else {
                System.out.println("PaymentSession not found with ID: " + psid);
            }
        } else {
            System.out.println("No psid in metadata or client_reference_id");
	        return null;
        }

	    Project projectentity = null;
		// save donation amount and update total project donation amount
	    if (success) {
            // You may need to determine the project logic from username, metadata, or another field
            Optional<Project> project = projectRepository.findById(ps.getProjectid()); 
            if (project.isPresent()) {
            	projectentity = project.get();
                Charity charity = new Charity();
                charity.setAmount(ps.getAmount());
                charity.setUsername(ps.getUsername());
                charity.setPaymentReference(ps.getId());
                charity.setPaymentStatus(ps.getStatus());
                charity.setProject(projectentity);
                charityRepository.save(charity);
                
                BigDecimal totalamount = charityRepository.sumoftotalcharityamount(ps.getProjectid());
                projectentity.setTotalCharityAmount(totalamount);
    	        System.out.println("Total Charity Amount >> " + totalamount);
                projectRepository.save(projectentity);
            }
            
            if (ps.isRegisteruser()) {
    	        System.out.println("Register new user.");
        		// register user after payment
            	// call auth to register a user and send email to reset his password (use ps data)
        		// allow user to set his account password to see his donations that he did on the projects
        		// redirect user to otp change password page he enters the otp from his email and change his password

    		    System.out.println("ENDPT>> " + authendpointapi + authregisteruserapi);
    	        RegisterUser registerUser = new RegisterUser(authendpointapi + authregisteruserapi, apikey, apisecret, "en", serverkey, serverpass, ps.getName(), ps.getUsername(), null);
    			String registerUserRes = registerUser.callAsPost();
    			if (registerUserRes == null) {
    				System.out.println("Error while calling register user");
    			}
    			else {

    				System.out.println("registerUserRes >> "  + registerUserRes);
    			}
    	
//    			JSONObject registerUserResponse = new JSONObject(registerUserRes);
//    			if (registerUserResponse == null || !registerUserResponse.has("user_id")) {
//
//    				return new ResponseEntity<String>(registerUserResponse.toString(), HttpStatus.OK);
//    			}
            
            }
            else {
    	        System.out.println("Existing user.");
            	
            }

    		// send email to username = email to thank him for his donation
            EmailDetailsRq rq = new EmailDetailsRq();
            rq.setName(ps.getName());
            rq.setRecipient(ps.getUsername()); // ps.getEmail()
            rq.setSubject("Thank You");
            String projecttitle = projectentity != null ? projectentity.getTitle() : "";
            rq.setMsgBody("Hi, " + ps.getName() + "<br>Thank you for your donation to the project " + projecttitle);
    		boolean sent = emailService.sendSimpleMail(rq);
        }
		return ps;
	}
	
}
