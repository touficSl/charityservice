package com.service.charity.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {

	ResponseEntity<?> callAuth(String apikey, String apisecret, String username, String token, String url, String lang);

}
