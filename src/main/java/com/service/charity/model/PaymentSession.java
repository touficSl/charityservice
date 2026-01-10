package com.service.charity.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paymentsessions")
public class PaymentSession {

    @Id
    @Column(name = "id", nullable = false, length = 450)
    private String id;

    @Column(name = "username", length = 1000)
    private String username;

    @Column(name = "name", length = 450)
    private String name;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "status", length = 450)
    private String status;

    @Column(name = "datetime")
    private Date datetime;

    @Column(name = "thirdpartystatus", length = 450)
    private String thirdpartystatus;

    @Column(name = "thirdpartyerror")
    private String thirdpartyerror;

    @Column(name = "amount")
    private BigDecimal amount; 
    
    @Column(name = "currency")
    private String currency; 

    @Column(name = "projectid", nullable = false)
    private Long projectid;
    
    private boolean registeruser;
    
    private String email;
    
    // Constructors
    public PaymentSession() {}

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

	public String getThirdpartystatus() {
		return thirdpartystatus;
	}

	public void setThirdpartystatus(String thirdpartystatus) {
		this.thirdpartystatus = thirdpartystatus;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public String getThirdpartyerror() {
		return thirdpartyerror;
	}

	public void setThirdpartyerror(String thirdpartyerror) {
		this.thirdpartyerror = thirdpartyerror;
	}

	public boolean isRegisteruser() {
		return registeruser;
	}

	public void setRegisteruser(boolean registeruser) {
		this.registeruser = registeruser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
