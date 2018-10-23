package com.api.utils;

import java.util.Arrays;

/**
 * 验证是否是系统端点
 */
public class ActuatorUtils {

	/**
	 * 系统端点：需要排序
	 */
	private static final String[] SYSTEM_ACTUATOR = {
		"/actuator",
		"/hystrix",
		"/hystrix.stream",
		"/turbine",
		"/turbine.stream"
	};
	
	static {
		Arrays.sort(SYSTEM_ACTUATOR);
	}
	
	/**
	 * 是否是系统端点
	 */
	public static final boolean actuator(String requestURL) {
		return Arrays.binarySearch(SYSTEM_ACTUATOR, requestURL) != -1;
	}
	
}
