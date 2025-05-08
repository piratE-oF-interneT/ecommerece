package com.app.community_report_service.ecceptions;

public class InternalServerException extends RuntimeException{

    public InternalServerException(String message){
        super(message);
    }
}
