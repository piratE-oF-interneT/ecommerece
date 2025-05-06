package com.app.community_report_service.entity;


import com.app.community_report_service.enums.Category;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Responder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String userId;

    @Enumerated(EnumType.STRING)
    private Category category;


    @OneToMany(mappedBy = "assignedResponder")
    private List<Report> reports;

    private boolean available;

    private Responder(){

    }

    public Responder(Long id, String name, String email, String phone, Category category, List<Report> reports , boolean available , String userId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.category = category;
        this.reports = reports;
        this.available=available;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
