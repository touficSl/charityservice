package com.service.charity.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.charity.builder.response.MessageResponse;
import com.service.charity.rest.call.GoogleRecaptcha;

import java.util.Locale;

@Service
public class GoogleRecaptchaServiceImpl implements GoogleRecaptchaService {

	@Value("${spring.google.recaptcha.api}") 
	private String api;

	@Value("${spring.google.recaptcha.secret}") 
	private String secret;

    @Autowired
    private MessageService messageService;

	@Override
	public ResponseEntity<?> verifyRecaptcha(Locale locale, String token) {

		try {

			if (token == null || token.trim().equals("")) {

				MessageResponse messageResponse = new MessageResponse(messageService.getMessage("recaptcha_required", locale), 410);
				return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
			}
			
			GoogleRecaptcha googleRecaptcha = new GoogleRecaptcha(api, token, secret);
			String recaptchaRes = googleRecaptcha.callAsPost();
			if (recaptchaRes == null) {

				MessageResponse messageResponse = new MessageResponse(messageService.getMessage("recaptcha_request_error", locale), 411);
				return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
			}
	
			JSONObject recaptcharesponse = new JSONObject(recaptchaRes);
			
			if (recaptcharesponse == null || !recaptcharesponse.has("success") || !recaptcharesponse.getBoolean("success")) {

				MessageResponse messageResponse = new MessageResponse(messageService.getMessage("recaptcha_verifying_validity_error", locale), 413);
				return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
			}
				
		} catch (Exception e) {
			e.printStackTrace();

			MessageResponse messageResponse = new MessageResponse(messageService.getMessage("recaptcha_verifying_error", locale), 414);
			return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
			
		}
		return null;
	}

}
