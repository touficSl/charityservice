package com.service.charity.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.charity.builder.request.ProjectRq;
import com.service.charity.config.Constants;
import com.service.charity.config.Utils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

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
    @Column(name = "estimation_time")
    private Date estimationTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "total_charity_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCharityAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATETIME_FORMAT)
    @Column(name = "date_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateTime;

    @Column(columnDefinition = "TEXT")
    private String type;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String status;

    @Column(columnDefinition = "BIT(1) DEFAULT b'1'")
    private Boolean enable;
    
    public Project() {
		super();
	}

	public Project(ProjectRq rq) {
		super();
		if (rq.getId() != null) this.id = rq.getId();
		
		this.reference = rq.getReference();
		this.title = rq.getTitle();
		this.description = rq.getDescription();
		this.estimationTime = Utils.convertStringToDate(rq.getEstimationTime(), null);
		this.cost = Utils.convertToBigDecimal(rq.getCost());
		this.totalCharityAmount = this.totalCharityAmount != null && this.totalCharityAmount.compareTo(BigDecimal.ZERO) > 0 ? totalCharityAmount : BigDecimal.ZERO;
		this.dateTime = new Date();
		this.type = rq.getType();
		this.status = rq.getStatus();
		this.enable = true;
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

    public Date getEstimationTime() { return estimationTime; }
    public void setEstimationTime(Date estimationTime) { this.estimationTime = estimationTime; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public BigDecimal getTotalCharityAmount() { return totalCharityAmount; }
    public void setTotalCharityAmount(BigDecimal totalCharityAmount) { this.totalCharityAmount = totalCharityAmount; }

    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getEnable() { return enable; }
    public void setEnable(Boolean enable) { this.enable = enable; }
}
