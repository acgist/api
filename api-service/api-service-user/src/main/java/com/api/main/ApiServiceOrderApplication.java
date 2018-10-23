package com.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrix
@ComponentScan("com.api.core")
@EnableEurekaClient
@SpringBootApplication
public class ApiServiceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceOrderApplication.class, args);
	}

}
