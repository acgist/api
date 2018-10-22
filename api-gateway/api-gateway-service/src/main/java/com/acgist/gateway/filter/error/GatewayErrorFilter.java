package com.acgist.gateway.filter.error;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.acgist.api.APICode;
import com.acgist.gateway.filter.BaseZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 异常处理，移除异常，防止执行SendErrorFilter
 */
@Component
public class GatewayErrorFilter extends BaseZuulFilter {

	// 禁止SendErrorFilter
	private static final String THROWABLE = "throwable";
	
	@Override
	public Object run() throws ZuulException {
		final RequestContext context = context();
		final HttpServletResponse response = response();
		final Throwable e = context.getThrowable();
		final APICode code = APICode.valueOfThrowable(e, response);
		final String message = APICode.message(code, e);
		error(code, message);
		disableSendErrorFilter(context);
		return null;
	}

	/**
	 * 禁用SendErrorFilter
	 */
	private void disableSendErrorFilter(RequestContext context) {
		context.remove(THROWABLE);
	}
	
	@Override
	public String filterType() {
		return FILTER_TYPE_ERROR;
	}

	@Override
	public int filterOrder() {
		return -1000;
	}

}
