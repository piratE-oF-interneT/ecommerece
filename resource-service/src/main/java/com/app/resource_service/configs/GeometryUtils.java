package com.app.resource_service.configs;


import com.app.resource_service.dto.Coordinates;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

@Component
public class GeometryUtils {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public static Point createPoint(Coordinates coordinates) {
        return geometryFactory.createPoint(new Coordinate(coordinates.getLongitude(), coordinates.getLatitude()));
    }
}
