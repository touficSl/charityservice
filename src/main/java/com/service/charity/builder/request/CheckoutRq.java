package com.service.charity.builder.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.service.charity.config.SanitizedStringDeserializer;

public class CheckoutRq {
	private Double amount;
    @JsonDeserialize(using = SanitizedStringDeserializer.class)
	private String name;
    @JsonDeserialize(using = SanitizedStringDeserializer.class)
	private String username; // email
    @JsonDeserialize(using = SanitizedStringDeserializer.class)
	private String message;
    @JsonDeserialize(using = SanitizedStringDeserializer.class)
	private String captchaToken;
    private Long projectid;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCaptchaToken() {
		return captchaToken;
	}
	public void setCaptchaToken(String captchaToken) {
		this.captchaToken = captchaToken;
	}
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
}
