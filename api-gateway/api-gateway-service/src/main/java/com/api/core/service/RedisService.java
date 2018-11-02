package com.api.core.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis 工具
 */
@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	public void set(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}
	
	public void del(String key) {
		redisTemplate.delete(key);
	}

}