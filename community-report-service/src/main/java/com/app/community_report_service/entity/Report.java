package com.app.community_report_service.entity;


import com.app.community_report_service.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Report {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String location;

    private String category;

    private Coordinates coordinates;

    private String reporterId;

    @ManyToOne
    private Responder assignedResponder;


    @CreationTimestamp
    private LocalDateTime reportedAt;


    @Enumerated(EnumType.STRING)
    private Status status;

    private String imageUrl;


    public Report(){

    }

    public Report(Long id, String title, String description, String location, String category, Coordinates coordinates, LocalDateTime reportedAt, Status status, String imageUrl , String reporterId , Responder responder) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
        this.coordinates = coordinates;
        this.reportedAt = reportedAt;
        this.status = status;
        this.imageUrl = imageUrl;
        this.reporterId = reporterId;
        this.assignedResponder = responder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public Responder getAssignedResponder() {
        return assignedResponder;
    }

    public void setAssignedResponder(Responder assignedResponder) {
        this.assignedResponder = assignedResponder;
    }
}
