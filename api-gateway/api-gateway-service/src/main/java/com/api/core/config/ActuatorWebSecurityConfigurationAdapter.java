package com.api.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 端点安全，需要添加spring-boot-starter-security
 */
@Order(0)
@Configuration
public class ActuatorWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.authorizeRequests()
//			.requestMatchers(EndpointRequest.toAnyEndpoint()).denyAll()
//			.requestMatchers(EndpointRequest.toAnyEndpoint()).access("hasIpAddress('0:0::/112') or hasIpAddress('192.168.1.0/24')")
//			.antMatchers("/actuator/**").denyAll()
			.antMatchers("/actuator/**").access("hasIpAddress('0:0::/112') or hasIpAddress('192.168.1.0/24')")
			.anyRequest().permitAll(); // 允许
	}
	
}