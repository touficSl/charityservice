package com.service.charity.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "project_images")
public class ProjectImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projectid", nullable = false)
    private Project project;

    @Column(length = 20)
    private String name;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String url;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String path;
    
    @Column(name = "`order`") // Maps to the DB column named `order`
    private Integer sortOrder; // Java field can be anything you want

    @Column(name = "date_time", nullable = false, updatable = false, insertable = false, columnDefinition = "Date DEFAULT CURRENT_Date")
    private Date dateTime;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public Integer getOrder() { return sortOrder; }
    public void setOrder(Integer order) { this.sortOrder = order; }

    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }
}
