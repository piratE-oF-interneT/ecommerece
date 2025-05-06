package com.app.image_processing_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean

    public RestTemplate getTemp(){
        return new RestTemplate();
    }
}
