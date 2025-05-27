package com.service.charity.builder.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.service.charity.config.SanitizedStringDeserializer;

public class ProjectRq {

    private Long id;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String reference;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String title;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String description;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String estimationTime;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String cost;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String type;

    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String status;

    private Boolean enable;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEstimationTime() { return estimationTime; }
    public void setEstimationTime(String estimationTime) { this.estimationTime = estimationTime; }

    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getEnable() { return enable; }
    public void setEnable(Boolean enable) { this.enable = enable; }
}
