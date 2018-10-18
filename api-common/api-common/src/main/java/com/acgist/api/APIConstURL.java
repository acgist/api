package com.acgist.api;

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
	
}
