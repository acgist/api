package com.acgist.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.acgist")
@EnableEurekaClient
@SpringBootApplication
public class ApiServiceResourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceResourcesApplication.class, args);
	}

}