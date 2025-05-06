package com.app.resource_service.resources;


import com.app.resource_service.enums.HospitalType;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Enumerated(EnumType.STRING)
    private HospitalType type;

    private int totalBeds;
    private int availableBeds;

    private int icuBeds;

    private boolean hasOxygen;
    private boolean hasEmergencyRoom;

    @OneToOne
    private Contact contact;

    // Geo location (for "nearby" queries)
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    public Hospital() {

    }

    public Hospital(Long id, String name, HospitalType type, int totalBeds, int availableBeds, int icuBeds, boolean hasOxygen, boolean hasEmergencyRoom, Contact contact, Point location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.totalBeds = totalBeds;
        this.availableBeds = availableBeds;
        this.icuBeds = icuBeds;
        this.hasOxygen = hasOxygen;
        this.hasEmergencyRoom = hasEmergencyRoom;
        this.contact = contact;
        this.location = location;
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

    public HospitalType getType() {
        return type;
    }

    public void setType(HospitalType type) {
        this.type = type;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public int getIcuBeds() {
        return icuBeds;
    }

    public void setIcuBeds(int icuBeds) {
        this.icuBeds = icuBeds;
    }

    public boolean isHasOxygen() {
        return hasOxygen;
    }

    public void setHasOxygen(boolean hasOxygen) {
        this.hasOxygen = hasOxygen;
    }

    public boolean isHasEmergencyRoom() {
        return hasEmergencyRoom;
    }

    public void setHasEmergencyRoom(boolean hasEmergencyRoom) {
        this.hasEmergencyRoom = hasEmergencyRoom;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}

