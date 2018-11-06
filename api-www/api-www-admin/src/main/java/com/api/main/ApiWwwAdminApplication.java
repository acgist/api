package com.api.main;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.core.controller.TurbineController;
import com.api.data.repository.BaseExtendRepositoryImpl;

import rx.subjects.PublishSubject;
import zipkin2.server.internal.EnableZipkinServer;

@EntityScan("com.api.data.**.entity")
@EnableAsync
@ComponentScan({"com.api.core", "com.api.data"})
//@EnableScheduling
@EnableEurekaClient
@EnableZipkinServer
@EnableTurbineStream
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.api.data.**.repository", repositoryBaseClass = BaseExtendRepositoryImpl.class)
@EnableHystrixDashboard
@EnableRedisHttpSession
@EnableTransactionManagement
public class ApiWwwAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWwwAdminApplication.class, args);
	}
	
	/**
	 * 重新turbine.stream监控
	 */
	@Bean
	public org.springframework.cloud.netflix.turbine.stream.TurbineController turbineController(PublishSubject<Map<String, Object>> hystrixSubject) {
		return new TurbineController(hystrixSubject);
	}
	
}
