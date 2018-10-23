package com.api.core.gateway.filter.post;

import org.springframework.stereotype.Component;

import com.api.core.gateway.component.APIType;
import com.api.core.gateway.component.SessionComponent;
import com.api.core.gateway.filter.BaseZuulFilter;
import com.api.core.gateway.response.APIResponse;
import com.api.utils.JSONUtils;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 将响应数据打包：SessionComponent
 */
@Component
public class PackageResponseFilter extends BaseZuulFilter {

	@Override
	public Object run() throws ZuulException {
		final RequestContext context = context();
		final SessionComponent session = sessionComponent();
		final APIType apiType = session.getApiType();
		Class<APIResponse> clazz = apiType == null ? APIResponse.class : apiType.responseClazz();
		final String responseJSON = context.getResponseBody();
		final APIResponse apiResponse = JSONUtils.jsonToJava(responseJSON, clazz);
		session.setResponse(apiResponse);
		return null;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE_POST;
	}

	@Override
	public int filterOrder() {
		return 100;
	}

}