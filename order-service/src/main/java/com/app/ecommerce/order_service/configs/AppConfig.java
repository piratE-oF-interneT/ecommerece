package com.app.ecommerce.order_service.configs;


import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getMapper(){

        return  new ModelMapper();
    }

    @Bean

    public Capability getCapability(final MeterRegistry meterRegistry){
        return new MicrometerCapability(meterRegistry);
    }

}


