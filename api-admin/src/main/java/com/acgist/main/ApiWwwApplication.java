package com.acgist.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.acgist")
@EnableEurekaClient
@EnableFeignClients("com.acgist.service")
@SpringBootApplication
public class ApiWwwApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWwwApplication.class, args);
	}

}
