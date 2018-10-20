package com.acgist.config;

/**
 * 链接管理
 */
public interface APIConstURL {
	
	/**
	 * 后台前缀
	 */
	String URL_ADMIN = "/admin";

	/**
	 * 服务前缀
	 */
	String URL_SERVICE = "/service";
	
	/**
	 * 熔断前缀
	 */
	String URL_FALLBACK = "/fallback";
	
	/**
	 * 熔断前缀
	 */
	String URL_FALLBACK_SERVICE = "/fallback" + URL_SERVICE;
	
	/**
	 * 网关前缀
	 */
	String URL_GATEWAY = "/gateway/api";
	
	// 创建订单
	String URL_ORDER = "/order";
	String URL_ADMIN_ORDER = URL_ADMIN + URL_ORDER;
	String URL_SERVICE_ORDER = URL_SERVICE + URL_ORDER;
	String URL_GATEWAY_ORDER = URL_GATEWAY + URL_ORDER;
	
	// 订单查询
	String URL_ORDER_QUERY = URL_ORDER + "/query";
	String URL_SERVICE_ORDER_QUERY = URL_SERVICE + URL_ORDER_QUERY;
	String URL_GATEWAY_ORDER_QUERY = URL_GATEWAY + URL_ORDER_QUERY;
	
	// 用户
	String URL_USER = "/user";
	
	// 用户登陆
	String URL_USER_LOGIN = URL_USER + "/login";
	String URL_SERVICE_USER_LOGIN = URL_SERVICE + URL_USER_LOGIN;
	
	// 获取用户鉴权信息
	String URL_USER_AUTHO = URL_USER + "/autho";
	String URL_SERVICE_USER_AUTHO = URL_SERVICE + URL_USER_AUTHO;
	
}
