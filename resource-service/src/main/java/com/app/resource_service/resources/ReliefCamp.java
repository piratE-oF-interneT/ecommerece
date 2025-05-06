package com.app.resource_service.resources;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
public class ReliefCamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Name of the relief camp

    private int totalCapacity;  // Maximum number of people the camp can accommodate
    private int currentOccupancy; // Current number of people in the camp

    private boolean hasFoodSupply;
    private boolean hasWaterSupply;
    private boolean hasSanitation;
    private boolean hasMedicalAid;

    private String managingOrg; // Organization responsible for the camp

    @OneToOne
    private Contact contact;

    // Location of the camp (geospatial coordinates)
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    // No-args constructor
    public ReliefCamp() {
    }

    // All-args constructor
    public ReliefCamp(Long id, String name, int totalCapacity, int currentOccupancy, boolean hasFoodSupply, boolean hasWaterSupply,
                      boolean hasSanitation, boolean hasMedicalAid, String managingOrg, Point location , Contact contact) {
        this.id = id;
        this.name = name;
        this.totalCapacity = totalCapacity;
        this.currentOccupancy = currentOccupancy;
        this.hasFoodSupply = hasFoodSupply;
        this.hasWaterSupply = hasWaterSupply;
        this.hasSanitation = hasSanitation;
        this.hasMedicalAid = hasMedicalAid;
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

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    public boolean isHasFoodSupply() {
        return hasFoodSupply;
    }

    public void setHasFoodSupply(boolean hasFoodSupply) {
        this.hasFoodSupply = hasFoodSupply;
    }

    public boolean isHasWaterSupply() {
        return hasWaterSupply;
    }

    public void setHasWaterSupply(boolean hasWaterSupply) {
        this.hasWaterSupply = hasWaterSupply;
    }

    public boolean isHasSanitation() {
        return hasSanitation;
    }

    public void setHasSanitation(boolean hasSanitation) {
        this.hasSanitation = hasSanitation;
    }

    public boolean isHasMedicalAid() {
        return hasMedicalAid;
    }

    public void setHasMedicalAid(boolean hasMedicalAid) {
        this.hasMedicalAid = hasMedicalAid;
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
