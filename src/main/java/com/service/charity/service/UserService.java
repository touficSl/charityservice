package com.service.charity.service;

import java.util.Locale;

import org.springframework.http.ResponseEntity;

import com.service.charity.builder.request.DonateRq;
import com.service.charity.model.Users;

public interface UserService {

	ResponseEntity<?> projectlist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username);

	ResponseEntity<?> donate(Locale locale, Users user, DonateRq rq);

}
