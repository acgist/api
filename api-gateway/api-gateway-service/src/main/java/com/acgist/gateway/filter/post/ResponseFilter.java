package com.acgist.gateway.filter.post;

import org.springframework.stereotype.Component;

import com.acgist.api.response.APIResponse;
import com.acgist.gateway.api.SessionComponent;
import com.acgist.gateway.filter.BaseZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 设置响应内容
 */
@Component
public class ResponseFilter extends BaseZuulFilter {

	@Override
	public Object run() throws ZuulException {
		final RequestContext context = context();
		final SessionComponent session = sessionComponent();
		final APIResponse apiResponse = session.getResponse();
		final String responseJSON = apiResponse.response();
		context.setResponseBody(responseJSON);
		return null;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE_POST;
	}

	@Override
	public int filterOrder() {
		return 998;
	}

}
