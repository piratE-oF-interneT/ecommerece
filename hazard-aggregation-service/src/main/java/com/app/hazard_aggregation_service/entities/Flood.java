package com.app.hazard_aggregation_service.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Flood {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private FloodProneCities city;


    @OneToMany
    private List<FloodData> floodDataList;

    public Flood(){

    }

    public Flood(Long id, FloodProneCities city) {

        this.id = id;
        this.city = city;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FloodProneCities getCity() {
        return city;
    }

    public void setCity(FloodProneCities city) {
        this.city = city;
    }

    public List<FloodData> getFloodDataList() {
        return floodDataList;
    }

    public void setFloodDataList(List<FloodData> floodDataList) {
        this.floodDataList = floodDataList;
    }
}
