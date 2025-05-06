package com.app.hazard_aggregation_service.entities;


import com.app.hazard_aggregation_service.apiResponse.flood.ForecastData;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Flood_City")
public class FloodProneCities {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;

    @OneToMany
    private List<FloodData> floodData;

    public FloodProneCities(){

    }

    public FloodProneCities(Long id, String name, Double latitude, Double longitude, List<FloodData> floodData) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.floodData = floodData;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<FloodData> getFloodData() {
        return floodData;
    }

    public void setFloodData(List<FloodData> floodData) {
        this.floodData = floodData;
    }
}
