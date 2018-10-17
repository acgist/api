package com.acgist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class PayConfig {

	@Bean
	public Retryer feignRetryer() {
//		return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(2), 2); // 时间间隔，最大重试时间，重试次数
		return Retryer.NEVER_RETRY;
	}
	
}
