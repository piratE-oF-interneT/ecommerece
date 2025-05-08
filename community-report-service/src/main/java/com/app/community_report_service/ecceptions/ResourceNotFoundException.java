package com.app.community_report_service.ecceptions;

import org.springframework.data.jpa.repository.JpaRepository;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
