package com.acgist.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.acgist.api.APICode;
import com.acgist.api.response.APIResponse;
import com.acgist.gateway.api.SessionComponent;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 过滤器，POST过滤器顺序要小于1000，否者不能设置返回的responseBody，参考：SendResponseFilter
 * 	异常处理需要禁用SendErrorFilter：移除异常
 */
public abstract class BaseZuulFilter extends ZuulFilter {

	// 过滤器类型
	protected static final String FILTER_TYPE_PRE = "pre";
	protected static final String FILTER_TYPE_POST = "post";
	protected static final String FILTER_TYPE_ROUTE = "route";
	protected static final String FILTER_TYPE_ERROR = "error";
	
	/**
	 * 是否通过验证
	 */
	public boolean permissions() {
		return context().sendZuulResponse();
	}
	
	/**
	 * 获取RequestContext
	 */
	public RequestContext context() {
		return RequestContext.getCurrentContext();
	}
	
	/**
	 * 获取SessionComponent
	 */
	public SessionComponent sessionComponent() {
		return SessionComponent.getInstance(context());
	}

	/**
	 * 请求
	 */
	public HttpServletRequest request() {
		return context().getRequest();
	}
	
	/**
	 * 响应
	 */
	public HttpServletResponse response() {
		return context().getResponse();
	}
	
	/**
	 * 失败
	 */
	public void error(APICode apiCode) {
		this.error(apiCode, apiCode.getMessage());
	}
	
	/**
	 * 失败
	 */
	public void error(APICode apiCode, String message) {
		this.error(HttpStatus.OK.value(), apiCode, message);
	}
	
	/**
	 * 失败
	 */
	public void error(int statusCode, APICode apiCode) {
		this.error(statusCode, apiCode, apiCode.getMessage());
	}
	
	/**
	 * 失败
	 */
	public void error(int status, APICode code, String message) {
		final RequestContext context = context();
		context.setSendZuulResponse(false);
		context.setResponseStatusCode(status);
		responseBody(APIResponse.builder().buildMessage(code, message));
	}
	
	/**
	 * 设置返回内容
	 */
	public void responseBody(APIResponse response) {
		final RequestContext context = context();
		context.setResponseBody(response.response());
	}
	
	@Override
	public boolean shouldFilter() {
		return true;
	}
	
}
