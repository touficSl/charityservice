package com.service.charity.service;

import java.util.Locale;

import org.springframework.http.ResponseEntity;

import com.service.charity.builder.request.SMSDetailsRq;

public interface SMSService {

	ResponseEntity<?> sendSMS(Locale locale, SMSDetailsRq details);

}
