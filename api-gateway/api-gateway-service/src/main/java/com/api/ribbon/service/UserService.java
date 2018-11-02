package com.api.ribbon.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.core.config.APIConstApplication;
import com.api.core.config.APIConstCache;
import com.api.core.user.config.APIConstUserURL;
import com.api.core.user.pojo.message.AuthoMessage;
import com.api.utils.HystrixUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 用户管理
 */
@Service
public class UserService {

	@Autowired
    public RestTemplate restTemplate;
	
	@Cacheable(cacheNames = APIConstCache.CACHE_KEY_USER_AUTHO, unless = "#result == null")
	@HystrixCommand(fallbackMethod = "authoFallback")
	public AuthoMessage autho(String name) {
		final String uri = HystrixUtils.buildService(APIConstApplication.API_SERVICE_USER, APIConstUserURL.URL_SERVICE_USER_AUTHO);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<?> entity = new HttpEntity<>(Map.of("name", name), headers);
		return restTemplate.postForEntity(uri, entity, AuthoMessage.class).getBody();
	}
	
	public AuthoMessage authoFallback(String name) {
		return null;
	}

}
