package com.app.hazard_aggregation_service.controller;

import com.app.hazard_aggregation_service.dto.EarthquakeDto;
import com.app.hazard_aggregation_service.services.apiService.EarthquakeService;
import com.app.hazard_aggregation_service.services.apiService.FloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HazardController {


    @Autowired
    private EarthquakeService earthquakeService;

    @Autowired
    private FloodService floodService;


    @GetMapping("/fetch")
    public String fetch(){

        earthquakeService.fetchData();
        return "fetch successful";
    }

    @GetMapping("/flood")
    public void flood(){

        floodService.fetchData();

    }

    @GetMapping("/earthquake")
    public ResponseEntity<List<EarthquakeDto>> getEarthquakes(){
        List<EarthquakeDto> earthquakeDtos = earthquakeService.getEarthquakeData();

        return new ResponseEntity<>(earthquakeDtos , HttpStatus.OK);
    }
}
