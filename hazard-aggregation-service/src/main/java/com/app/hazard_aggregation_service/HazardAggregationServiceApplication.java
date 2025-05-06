package com.app.hazard_aggregation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.app.hazard_aggregation_service.entities")
@EnableJpaRepositories(basePackages = "com.app.hazard_aggregation_service.repositories")
public class HazardAggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazardAggregationServiceApplication.class, args);
	}

}
