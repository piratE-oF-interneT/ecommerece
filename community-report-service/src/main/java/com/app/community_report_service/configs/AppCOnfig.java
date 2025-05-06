package com.app.community_report_service.configs;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCOnfig {

    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }
}
