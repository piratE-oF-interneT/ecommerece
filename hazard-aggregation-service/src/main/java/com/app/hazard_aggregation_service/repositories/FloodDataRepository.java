package com.app.hazard_aggregation_service.repositories;

import com.app.hazard_aggregation_service.entities.FloodData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloodDataRepository extends JpaRepository<FloodData,Long> {
}
