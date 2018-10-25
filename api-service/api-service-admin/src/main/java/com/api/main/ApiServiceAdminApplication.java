package com.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;

import zipkin2.server.internal.EnableZipkinServer;

@EnableTurbine
@EnableHystrix
@ComponentScan("com.api.core")
@EnableEurekaClient
@EnableZipkinServer
@EnableHystrixDashboard
@SpringBootApplication
public class ApiServiceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceAdminApplication.class, args);
	}
	
}
