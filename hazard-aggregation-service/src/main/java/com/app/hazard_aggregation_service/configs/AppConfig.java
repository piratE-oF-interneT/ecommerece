package com.app.hazard_aggregation_service.configs;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }

}
