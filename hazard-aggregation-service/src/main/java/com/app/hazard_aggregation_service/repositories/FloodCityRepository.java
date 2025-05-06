package com.app.hazard_aggregation_service.repositories;


import com.app.hazard_aggregation_service.entities.FloodProneCities;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloodCityRepository extends JpaRepository<FloodProneCities , Long> {
    FloodProneCities findByLatitudeAndLongitude(Double latitude, Double longitude);
}
