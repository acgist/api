package com.acgist.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableTurbine
@EnableHystrix
@ComponentScan("com.acgist")
@EnableEurekaClient
@EnableFeignClients("com.acgist.service")
@EnableHystrixDashboard
@SpringBootApplication
public class ApiServiceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceAdminApplication.class, args);
	}
	
}
