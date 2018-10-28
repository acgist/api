package com.api.main;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.api.core.controller.TurbineController;

import rx.subjects.PublishSubject;
import zipkin2.server.internal.EnableZipkinServer;

//@EnableAsync
@ComponentScan("com.api.core")
//@EnableScheduling
@EnableEurekaClient
@EnableZipkinServer
//@EnableTurbine
@EnableTurbineStream
@SpringBootApplication
@EnableHystrixDashboard
public class ApiWwwAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWwwAdminApplication.class, args);
	}
	
	@Bean
	public org.springframework.cloud.netflix.turbine.stream.TurbineController turbineController(PublishSubject<Map<String, Object>> hystrixSubject) {
		return new TurbineController(hystrixSubject);
	}
	
}
