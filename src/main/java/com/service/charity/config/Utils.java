package com.service.charity.config;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	public static boolean isapiauthorized(String url, List<String> authorizedapis) {
		for (String authapi : authorizedapis)
			if (url.contains(authapi))
				return true;
		return false;
	}

	public static String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	public static String generateUUID(String prefix) {
        return prefix + UUID.randomUUID().toString(); 
    }

	public static String generateOTP(int length) {
		String numbers = "0123456789";
		Random rndm_method = new Random();
		char[] otp = new char[length];
		for (int i = 0; i < length; i++)
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		
		return new String(otp);
	}


	public static String returnOTPSMSMessage() {
		return Constants.OTP_SMS_MESSAG_KEY + generateOTP(6);
	}
}
