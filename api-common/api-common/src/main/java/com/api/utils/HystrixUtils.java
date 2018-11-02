package com.api.utils;

/**
 * hystrix工具
 */
public class HystrixUtils {

	// HTTP
	private static final String HTTP = "http://";
	
	/**
	 * 创建服务地址
	 * @param service 服务
	 * @param path 地址
	 * @return 服务地址
	 */
	public static final String buildService(String service, String ... paths) {
		final StringBuffer builder = new StringBuffer(HTTP);
		builder.append(service);
		for (String path : paths) {
			builder.append(path);
		}
		return builder.toString();
	}
	
}
