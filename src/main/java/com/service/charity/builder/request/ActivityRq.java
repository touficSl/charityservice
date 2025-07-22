package com.service.charity.builder.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.service.charity.config.SanitizedStringDeserializer;

public class ActivityRq {

    private Long id;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String reference;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String title;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String description;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String startdate;
    
    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String enddate;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String cost;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String type;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String status;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String fblink;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String instalink;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String tiktoklink;

    private Boolean enable;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String currency;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getEnable() { return enable; }
    public void setEnable(Boolean enable) { this.enable = enable; }
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getFblink() {
		return fblink;
	}
	public void setFblink(String fblink) {
		this.fblink = fblink;
	}
	public String getInstalink() {
		return instalink;
	}
	public void setInstalink(String instalink) {
		this.instalink = instalink;
	}
	public String getTiktoklink() {
		return tiktoklink;
	}
	public void setTiktoklink(String tiktoklink) {
		this.tiktoklink = tiktoklink;
	}
}
