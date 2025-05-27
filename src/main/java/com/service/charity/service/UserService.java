package com.service.charity.service;

import java.util.Locale;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import com.service.charity.builder.request.CheckoutRq;
import com.service.charity.builder.request.DonateRq;
import com.service.charity.builder.request.ProjectListRequest;
import com.service.charity.model.PaymentSession;
import com.service.charity.model.Users;

public interface UserService {

	ResponseEntity<?> projectlist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username);

	ResponseEntity<?> donate(Locale locale, Users user, DonateRq rq);

	ResponseEntity<?> projectlist(Locale locale, ProjectListRequest request);

	ResponseEntity<?> projectdetails(Locale locale, Long id);

	ResponseEntity<?> downloadfile(String fileName);
	
	ResponseEntity<?> returnbase64file(String fileName);

	PaymentSession handlepaymentsession(String token, CheckoutRq rq, boolean isregisteruser);

	PaymentSession handlepaymentwebhook(JSONObject json, String eventType);
	
}
