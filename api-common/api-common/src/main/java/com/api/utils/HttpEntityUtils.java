package com.api.utils;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * HttpEntity工具
 */
public class HttpEntityUtils {

	/**
	 * 表单请求
	 */
	public static final HttpEntity<Map<String, Object>> formEntity(Map<String, Object> map) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return new HttpEntity<Map<String, Object>>(map, headers);
	}
	
	/**
	 * JSON请求数据
	 */
	public static final HttpEntity<Object> jsonEntity(Object object) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new HttpEntity<Object>(object, headers);
	}
	
}
