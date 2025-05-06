package com.app.resource_service.repository;

import com.app.resource_service.resources.Hospital;
import com.app.resource_service.resources.MobileMedicalCamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MobileMedicalCampRepository extends JpaRepository<MobileMedicalCamp , Long>{

    @Query(value = """
    SELECT * FROM MobileMedicalCamp
    WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(:lon, :lat), 4326), :radius)
    """, nativeQuery = true)
    List<Hospital> findNearby(@Param("lat") double lat, @Param("lon") double lon, @Param("radius") double radiusInMeters);
}
