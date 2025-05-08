package com.app.community_report_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommunityReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityReportServiceApplication.class, args);
	}

}
