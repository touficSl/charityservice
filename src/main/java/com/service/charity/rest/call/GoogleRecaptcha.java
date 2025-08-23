package com.service.charity.rest.call;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class GoogleRecaptcha extends RestCallHandler{ 

	private String secret;
	private String token;
	
	public GoogleRecaptcha(String api, String token, String secret) {
		super(api);
		this.secret = secret;
		this.token = token;
	} 
	 
	@Override
	public void constructHeaders() {
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	}

    @Override
    public void constructBody() {
        // ✅ Simple form-encoded string
        this.body = "secret=" + URLEncoder.encode(secret, StandardCharsets.UTF_8) +
                   "&response=" + URLEncoder.encode(token, StandardCharsets.UTF_8);
    }

}
