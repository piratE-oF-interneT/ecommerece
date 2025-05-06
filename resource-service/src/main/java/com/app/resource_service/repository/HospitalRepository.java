package com.app.resource_service.repository;


import com.app.resource_service.resources.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital , Long> {

    @Query(value = """
    SELECT * FROM hospital
    WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(:lon, :lat), 4326), :radius)
    """, nativeQuery = true)
    List<Hospital> findNearby(@Param("lat") double lat, @Param("lon") double lon, @Param("radius") double radiusInMeters);

}
