package com.app.hazard_aggregation_service.exceptions;

public class EarthquakeDataFetchException extends RuntimeException{

    public EarthquakeDataFetchException(String message){
        super(message);
    }
}
