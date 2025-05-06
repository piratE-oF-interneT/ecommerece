package com.app.resource_service.dto;

import com.app.resource_service.enums.HospitalType;

public class HospitalCreationDto {

    private String name;

    private String type;

    private int totalBeds;


    private int icuBeds;

    private boolean hasOxygen;

    private boolean hasEmergencyRoom;

    private String phone;

    private String email;

    private Coordinates location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
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
