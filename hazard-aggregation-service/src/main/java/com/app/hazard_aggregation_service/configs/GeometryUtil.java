package com.app.hazard_aggregation_service.configs;


import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

@Component
public class GeometryUtil {

    private static final GeometryFactory geometryFactory = new GeometryFactory();
    public static Point getPoint(double longitude , double latitude){

        return geometryFactory.createPoint(new Coordinate(longitude,latitude));
    }

}
