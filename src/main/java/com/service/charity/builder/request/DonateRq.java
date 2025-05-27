package com.service.charity.builder.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.service.charity.config.SanitizedStringDeserializer;

public class DonateRq {

    private Long projectid;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String username;

    private BigDecimal amount;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String paymentReference;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String paymentStatus;

    private Timestamp dateTime;


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getPaymentReference() { return paymentReference; }
    public void setPaymentReference(String paymentReference) { this.paymentReference = paymentReference; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public Timestamp getDateTime() { return dateTime; }
    public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
}
