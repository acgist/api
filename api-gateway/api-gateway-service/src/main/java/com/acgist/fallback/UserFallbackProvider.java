package com.acgist.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.acgist.api.API;
import com.acgist.api.APICode;
import com.acgist.api.response.APIResponse;
import com.acgist.config.APIConstApplication;

/**
 * 用户-熔断器
 */
@Component
public class UserFallbackProvider implements FallbackProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserFallbackProvider.class);
	
	@Override
	public String getRoute() {
		return APIConstApplication.API_SERVICE_USER;
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		LOGGER.warn("服务不可用，服务名称：{}", getRoute());
		return new ClientHttpResponse() {
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return headers;
			}
			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream(APIResponse.builder().buildMessage(APICode.CODE_1002).toString().getBytes(API.DEFAULT_CHARSET));
			}
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE;
			}
			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.value();
			}
			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
			}
			@Override
			public void close() {
			}
		};
	}

}
