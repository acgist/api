package com.acgist.api.service.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApiServicePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServicePayApplication.class, args);
	}
	
}
