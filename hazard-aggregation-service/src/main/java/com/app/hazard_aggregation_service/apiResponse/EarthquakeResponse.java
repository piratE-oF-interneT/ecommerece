package com.app.hazard_aggregation_service.apiResponse;

import com.app.hazard_aggregation_service.apiResponse.earthquake.EarthquakeFeature;

import java.util.List;

public class EarthquakeResponse {

   private List<EarthquakeFeature> features;

    public List<EarthquakeFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<EarthquakeFeature> features) {
        this.features = features;
    }
}
