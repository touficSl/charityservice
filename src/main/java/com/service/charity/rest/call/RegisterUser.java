package com.service.charity.rest.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class RegisterUser extends RestCallHandler{ 

	private String apikey;
	private String apisecret;
	private String lang;

	private String serverkey;
	private String serverpass;

	private String first_name;

	private String last_name;

	private String username;

	private String password;

	private String user_role;

	private String email;

	private String captchaToken;
	
	
	public RegisterUser(String api, String apikey, String apisecret, String lang, String serverkey, String serverpass, String first_name,
			String username, String captchaToken) {
		super(api);
		this.apikey = apikey;
		this.lang = lang;
		this.serverkey = serverkey;
		this.serverpass = serverpass;
		this.first_name = first_name;
		this.last_name = "";
		this.username = username;
		this.password = "_";
		this.user_role = "_";
		this.email = username;
		this.captchaToken = captchaToken;
		this.apisecret = apisecret;
	}

	@Override
	public void constructHeaders() {
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.APPLICATION_JSON);
		this.headers.set("serverkey", serverkey); 
		this.headers.set("serverpass", serverpass);
		this.headers.set("apikey", apikey); 
		this.headers.set("apisecret", apisecret); 
		this.headers.set("Accept-Language", lang); 
		
		try {
		    System.out.println("HEADERS>> ");
			for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
			    System.out.println(entry.getKey() + ": " + entry.getValue());
			}

		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void constructBody() {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("first_name", first_name);
		map.put("last_name", last_name);
		map.put("username", username);
		map.put("password", password);
		map.put("user_role", user_role);
		map.put("email", email);
		map.put("captchaToken", captchaToken);
		
		JSONObject request = new JSONObject(map);
	    System.out.println("BODY>> ");
	    System.out.println(request.toString());
		this.body = request.toString();
	}

}
