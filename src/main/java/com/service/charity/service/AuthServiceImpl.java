package com.service.charity.service;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.charity.builder.response.MessageResponse;
import com.service.charity.config.Utils;
import com.service.charity.model.Users;
import com.service.charity.rest.call.VerifyAuth;



@Service
public class AuthServiceImpl implements AuthService {

	@Value("${spring.auth.endpoint.api}") 
	private String authendpointapi;
	@Value("${spring.service.auth.api}") 
	private String authapi;

	@Autowired
	MessageService messageService;

	
	@Override
	public ResponseEntity<?> callAuth(String apikey, String apisecret, String username, String token, String url, String lang) {
		try {

			if (token == null || token.trim().equals("")) 
				return new ResponseEntity<MessageResponse>(new MessageResponse("Token is required", 310), HttpStatus.OK);
			
			VerifyAuth verifyAuth = new VerifyAuth(authendpointapi + authapi, apikey, apisecret, username, token, lang);
			String verifyAuthRes = verifyAuth.callAsPost();
			if (verifyAuthRes == null)
				return new ResponseEntity<MessageResponse>(new MessageResponse("Error while calling verify Auth service", 311), HttpStatus.OK);
	
			JSONObject verifyAuthResponse = new JSONObject(verifyAuthRes);

			if (verifyAuthResponse == null || !verifyAuthResponse.has("user_id")) {

				return new ResponseEntity<String>(verifyAuthResponse.toString(), HttpStatus.OK);
			}

			Users user = new Users(verifyAuthResponse);
			
			//check api authorization
			if (!Utils.isapiauthorized(url, null, user.getAuthorizedapis()))
				return new ResponseEntity<MessageResponse>(new MessageResponse("API unauthorized", 312), HttpStatus.OK);
			
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MessageResponse>(new MessageResponse(e.getMessage(), 3141), HttpStatus.OK);
		}
	}
	
}
