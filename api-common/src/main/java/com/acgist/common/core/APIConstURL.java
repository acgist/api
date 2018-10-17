package com.acgist.common.core;

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
	
	// 交易
	String URL_PAY = "/pay";
	String URL_ADMIN_PAY = URL_ADMIN + URL_PAY;
	String URL_SERVICE_PAY = URL_SERVICE + URL_PAY;
	String URL_GATEWAY_PAY = URL_GATEWAY + URL_PAY;
	
	// 交易查询
	String URL_PAY_QUERY = URL_PAY + "/query";
	String URL_SERVICE_PAY_QUERY = URL_SERVICE + URL_PAY_QUERY;
	String URL_GATEWAY_PAY_QUERY = URL_GATEWAY + URL_PAY_QUERY;
	
	// 交易退款
	String URL_PAY_DRAWBACK = URL_PAY + "/drawback";
	String URL_SERVICE_PAY_DRAWBACK = URL_SERVICE + URL_PAY_DRAWBACK;
	String URL_GATEWAY_PAY_DRAWBACK = URL_GATEWAY + URL_PAY_DRAWBACK;
	
}
