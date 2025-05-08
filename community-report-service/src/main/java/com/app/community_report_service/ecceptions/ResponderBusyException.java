package com.app.community_report_service.ecceptions;

public class ResponderBusyException extends RuntimeException{

    public ResponderBusyException(String message){
        super(message);
    }
}
