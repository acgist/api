package com.api.core.www.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * session跨域配置
 */
@Configuration
public class CorsFilterConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilterConfig.class);
	
	@Bean
	public CorsFilter corsFilter() {
		LOGGER.warn("跨域配置添加了所有的域、头、方法请根据实际情况配置");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(CorsConfiguration.ALL);
		config.addAllowedHeader(CorsConfiguration.ALL);
		config.addAllowedMethod(CorsConfiguration.ALL);;
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
