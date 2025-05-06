package com.app.hazard_aggregation_service.apiResponse.earthquake;

public class EarthquakeFeature {
    private EarthquakeProperties properties;
    private EarthquakeGeometry geometry;
    private String id;

    public EarthquakeProperties getProperties() {
        return properties;
    }

    public void setProperties(EarthquakeProperties properties) {
        this.properties = properties;
    }

    public EarthquakeGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(EarthquakeGeometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
