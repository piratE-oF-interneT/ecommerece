package com.app.community_report_service.dto;

import org.springframework.web.multipart.MultipartFile;

public class RescueRequestDto {

    private String location;

    private String image;

    public RescueRequestDto(){


    }

    public RescueRequestDto(String location, String image) {
        this.location = location;
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
