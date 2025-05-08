package com.app.community_report_service.entity;


import com.app.community_report_service.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity
public class Report {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private String reporterId;

    private String location;


    @Lob
    @Column(length = 1000000)
    private byte[] imageBytes;

    @ManyToOne
    private Responder assignedResponder;


    @CreationTimestamp
    private LocalDateTime reportedAt;


    @Enumerated(EnumType.STRING)
    private Status status;


    @Autowired
    private String path;




    public Report(){

    }

    public Report(Long id, String title, String description, String category, String reporterId, String location, byte[] imageBytes, Responder assignedResponder, LocalDateTime reportedAt, Status status, String path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.reporterId = reporterId;
        this.location = location;
        this.imageBytes = imageBytes;
        this.assignedResponder = assignedResponder;
        this.reportedAt = reportedAt;
        this.status = status;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
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


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
