package com.app.hazard_aggregation_service.apiResponse.flood;

import jakarta.persistence.Entity;

import java.util.List;



public class CityForecast {

    private String cityName;

    private Double latitude;  // Latitude of the city
    private Double longitude;  // Longitude of the city
    private List<ForecastData> forecast;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<ForecastData> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastData> forecast) {
        this.forecast = forecast;
    }
}
