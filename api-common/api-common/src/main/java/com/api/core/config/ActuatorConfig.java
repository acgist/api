package com.api.core.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 内网IP地址配置
 */
@Configuration
public class ActuatorConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActuatorConfig.class);
	
	@Value("${system.actuator.ip.addresses:localhost}")
	private String ipAddresses;
	@Value("${system.actuator.ant.matchers:/actuator/**}")
	private String antMatchers;
	// actuator内网验证表达式
	private String actuatorIpAddresses;
	private String[] actuatorAntMatchers;
	
	@PostConstruct
	public void init() {
		initActuatorIpAddresses();
		initActuatorAntMatchers();
		LOGGER.info("初始化端点拦截IP匹配配置：{}", actuatorIpAddresses);
		LOGGER.info("初始化端点拦截地址匹配配置：{}", this.antMatchers);
	}

	private void initActuatorIpAddresses() {
		final String[] ipAddresses = this.ipAddresses.split(",");
		if(ipAddresses.length == 0) {
			actuatorIpAddresses = "localhost";
			return;
		}
		final StringBuffer matcher = new StringBuffer();
		matcher.append("hasIpAddress('").append(ipAddresses[0]).append("')");
		if(ipAddresses.length == 1) {
			actuatorIpAddresses = matcher.toString();
			return;
		}
		for (int index = 1; index < ipAddresses.length; index++) {
			matcher.append(" or hasIpAddress('").append(ipAddresses[index]).append("')");
		}
		actuatorIpAddresses = matcher.toString();
	}
	
	private void initActuatorAntMatchers() {
		final String[] antMatchers = this.antMatchers.split(",");
		this.actuatorAntMatchers = antMatchers;
	}

	public String getActuatorIpAddresses() {
		return actuatorIpAddresses;
	}

	public String[] getActuatorAntMatchers() {
		return actuatorAntMatchers;
	}
	
}
