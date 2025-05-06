package com.app.resource_service.dto;

import com.app.resource_service.resources.Contact;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import org.locationtech.jts.geom.Point;

public class ReliefCampCreationDto {

    private Long id;

    private String name; // Name of the relief camp

    private int totalCapacity;  // Maximum number of people the camp can accommodate



    private String managingOrg; // Organization responsible for the camp


    private String phone;

    private String email;


    private Coordinates location;

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

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getManagingOrg() {
        return managingOrg;
    }

    public void setManagingOrg(String managingOrg) {
        this.managingOrg = managingOrg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }
}
