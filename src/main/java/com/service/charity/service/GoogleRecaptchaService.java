package com.service.charity.service;

import org.springframework.http.ResponseEntity;
import java.util.Locale;

public interface GoogleRecaptchaService {

	ResponseEntity<?> verifyRecaptcha(Locale locale, String token);

}
