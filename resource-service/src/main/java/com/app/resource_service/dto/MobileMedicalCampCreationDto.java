package com.app.resource_service.dto;

import com.app.resource_service.resources.Contact;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import org.locationtech.jts.geom.Point;

public class MobileMedicalCampCreationDto {

    private String name; // Name of the medical camp


    private int availableDoctors;
    private int availableNurses;
    private int availableAmbulances;


    private String managingOrg; // Organization responsible


    private String phone;
    private String email;

    // Location of the camp (geospatial coordinates)
    private Coordinates location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableDoctors() {
        return availableDoctors;
    }

    public void setAvailableDoctors(int availableDoctors) {
        this.availableDoctors = availableDoctors;
    }

    public int getAvailableNurses() {
        return availableNurses;
    }

    public void setAvailableNurses(int availableNurses) {
        this.availableNurses = availableNurses;
    }

    public int getAvailableAmbulances() {
        return availableAmbulances;
    }

    public void setAvailableAmbulances(int availableAmbulances) {
        this.availableAmbulances = availableAmbulances;
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

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
