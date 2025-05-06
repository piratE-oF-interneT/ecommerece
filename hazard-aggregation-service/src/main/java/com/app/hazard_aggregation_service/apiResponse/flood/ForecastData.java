package com.app.hazard_aggregation_service.apiResponse.flood;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;


public class ForecastData {

    private LocalDateTime forecastDate;

    private Double temperature;

    private Double precipitation;


    public LocalDateTime getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDateTime forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }
}
