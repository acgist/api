package com.acgist.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.acgist.repository.BaseExtendRepositoryImpl;

@EntityScan("com.acgist.**.entity")
@EnableHystrix
@ComponentScan("com.acgist")
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.acgist.**.repository", repositoryBaseClass = BaseExtendRepositoryImpl.class)
@SpringBootApplication
@EnableTransactionManagement
public class ApiServiceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceOrderApplication.class, args);
	}

}
