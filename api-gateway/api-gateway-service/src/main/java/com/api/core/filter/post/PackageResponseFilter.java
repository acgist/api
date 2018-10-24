package com.api.core.filter.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.core.filter.BaseZuulFilter;
import com.api.core.gateway.APIType;
import com.api.core.gateway.SessionComponent;
import com.api.core.gateway.response.APIResponse;
import com.api.core.stream.GatewayMesssageSender;
import com.api.utils.JSONUtils;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 将响应数据打包：SessionComponent
 */
@Component
public class PackageResponseFilter extends BaseZuulFilter {

	@Autowired
	private GatewayMesssageSender sender;
	
	@Override
	public Object run() throws ZuulException {
		final RequestContext context = context();
		final SessionComponent session = sessionComponent();
		final APIType apiType = session.getApiType();
		Class<APIResponse> clazz = apiType == null ? APIResponse.class : apiType.responseClazz();
		final String responseJSON = context.getResponseBody();
		final APIResponse apiResponse = JSONUtils.jsonToJava(responseJSON, clazz);
		sender.send(responseJSON);
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
