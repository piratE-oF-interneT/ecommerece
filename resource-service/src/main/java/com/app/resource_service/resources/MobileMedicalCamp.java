package com.app.resource_service.resources;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
public class MobileMedicalCamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Name of the medical camp


    private int availableDoctors;
    private int availableNurses;
    private int availableAmbulances;

    private boolean hasMedicines;
    private boolean hasMedicalSupplies; // Bandages, syringes, etc.
    private boolean hasFirstAid;

    private String managingOrg; // Organization responsible

    @OneToOne
    private Contact contact;

    // Location of the camp (geospatial coordinates)
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    // No-args constructor
    public MobileMedicalCamp() {
    }

    // All-args constructor
    public MobileMedicalCamp(Long id, String name, int availableDoctors, int availableNurses, int availableAmbulances,
                             boolean hasMedicines, boolean hasMedicalSupplies, boolean hasFirstAid, String managingOrg, Point location , Contact contact) {
        this.id = id;
        this.name = name;

        this.availableDoctors = availableDoctors;
        this.availableNurses = availableNurses;
        this.availableAmbulances = availableAmbulances;
        this.hasMedicines = hasMedicines;
        this.hasMedicalSupplies = hasMedicalSupplies;
        this.hasFirstAid = hasFirstAid;
        this.managingOrg = managingOrg;
        this.location = location;
        this.contact = contact;
    }

    // Getters and Setters
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

    public boolean isHasMedicines() {
        return hasMedicines;
    }

    public void setHasMedicines(boolean hasMedicines) {
        this.hasMedicines = hasMedicines;
    }

    public boolean isHasMedicalSupplies() {
        return hasMedicalSupplies;
    }

    public void setHasMedicalSupplies(boolean hasMedicalSupplies) {
        this.hasMedicalSupplies = hasMedicalSupplies;
    }

    public boolean isHasFirstAid() {
        return hasFirstAid;
    }

    public void setHasFirstAid(boolean hasFirstAid) {
        this.hasFirstAid = hasFirstAid;
    }

    public String getManagingOrg() {
        return managingOrg;
    }

    public void setManagingOrg(String managingOrg) {
        this.managingOrg = managingOrg;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public double getLatitude() {
        return location != null ? location.getY() : 0.0;
    }

    public double getLongitude() {
        return location != null ? location.getX() : 0.0;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
