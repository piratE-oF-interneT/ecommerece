package com.app.resource_service.repository;

import com.app.resource_service.resources.Hospital;
import com.app.resource_service.resources.ReliefCamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReliefCampRepository extends JpaRepository<ReliefCamp , Long> {

    @Query(value = """
    SELECT * FROM ReliefCamp
    WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(:lon, :lat), 4326), :radius)
    """, nativeQuery = true)
    List<ReliefCamp> findNearby(@Param("lat") double lat, @Param("lon") double lon, @Param("radius") double radiusInMeters);
}
