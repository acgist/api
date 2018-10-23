package com.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.api.core")
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ApiGatewayWwwApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayWwwApplication.class, args);
	}
	
}
