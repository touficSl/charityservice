package com.service.charity.builder.response;
import java.math.BigDecimal;
import java.util.List;

public class CharityStatisticsDTO {

    private Long totalCharities;
    private BigDecimal totalCharitiesAmount;
    private List<ProjectCharityAmount> totalCharitiesAmountPerProject;
    private Long totalProjects;

    // Getters and Setters
    public Long getTotalCharities() {
        return totalCharities;
    }

    public void setTotalCharities(Long totalCharities) {
        this.totalCharities = totalCharities;
    }

    public BigDecimal getTotalCharitiesAmount() {
        return totalCharitiesAmount;
    }

    public void setTotalCharitiesAmount(BigDecimal totalCharitiesAmount) {
        this.totalCharitiesAmount = totalCharitiesAmount;
    }

    public List<ProjectCharityAmount> getTotalCharitiesAmountPerProject() {
        return totalCharitiesAmountPerProject;
    }

    public void setTotalCharitiesAmountPerProject(List<ProjectCharityAmount> totalCharitiesAmountPerProject) {
        this.totalCharitiesAmountPerProject = totalCharitiesAmountPerProject;
    }

    public Long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(Long totalProjects) {
        this.totalProjects = totalProjects;
    }
}
