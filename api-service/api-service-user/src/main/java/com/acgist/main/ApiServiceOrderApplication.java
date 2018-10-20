package com.acgist.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrix
@ComponentScan("com.acgist")
@EnableEurekaClient
@SpringBootApplication
public class ApiServiceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceOrderApplication.class, args);
	}

}
