package com.service.charity.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

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

    @Column(name = "order")
    private Integer order;

    @Column(name = "date_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateTime;

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

    public Integer getOrder() { return order; }
    public void setOrder(Integer order) { this.order = order; }

    public Timestamp getDateTime() { return dateTime; }
    public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }
}
