package com.app.hazard_aggregation_service.apiResponse.earthquake;

import java.util.List;

public class EarthquakeGeometry {

    private List<Double> coordinates;

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
