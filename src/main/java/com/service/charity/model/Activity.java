package com.service.charity.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.charity.builder.request.ActivityRq;
import com.service.charity.config.Constants;
import com.service.charity.config.Utils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String reference;

    @Column(nullable = false, length = 700)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    @Column(name = "startdate")
    private Date startdate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    @Column(name = "enddate")
    private Date enddate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATETIME_FORMAT)
    @Column(name = "date_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateTime;

    @Column(columnDefinition = "TEXT")
    private String type;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String status;

    @Column(columnDefinition = "BIT(1) DEFAULT b'1'")
    private Boolean enable;

    private String currency;

    private String fblink;

    private String instalink;

    private String tiktoklink;

    @Transient
    private List<String> images;
    
    public Activity() {
		super();
	}

	public Activity(ActivityRq rq) {
		super();
		if (rq.getId() != null) this.id = rq.getId();
		
		this.reference = rq.getReference();
		this.title = rq.getTitle();
		this.description = rq.getDescription();
		this.startdate = Utils.convertStringToDate(rq.getStartdate(), Constants.DATE_FORMAT);
		this.enddate = Utils.convertStringToDate(rq.getEnddate(), Constants.DATE_FORMAT);
		this.cost = Utils.convertToBigDecimal(rq.getCost());
		this.dateTime = new Date();
		this.type = rq.getType();
		this.status = rq.getStatus();
		this.enable = true;
		this.currency = rq.getCurrency();
		this.fblink = rq.getFblink();
		this.instalink = rq.getInstalink();
		this.tiktoklink = rq.getTiktoklink();
	}
    
	// Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
    
    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getEnable() { return enable; }
    public void setEnable(Boolean enable) { this.enable = enable; }

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
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
