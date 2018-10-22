package com.acgist.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 过滤器
 */
public abstract class BaseZuulFilter extends ZuulFilter {

	// 过滤器类型
	protected static final String FILTER_TYPE_PRE = "pre";
	protected static final String FILTER_TYPE_POST = "post";
	protected static final String FILTER_TYPE_ROUTE = "route";
	protected static final String FILTER_TYPE_ERROR = "error";
	
	/**
	 * 获取上下文
	 */
	public RequestContext context() {
		return RequestContext.getCurrentContext();
	}
	
}
