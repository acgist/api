package com.api.core.gateway.filter.post;

import org.springframework.stereotype.Component;

import com.api.core.gateway.component.APIType;
import com.api.core.gateway.component.SessionComponent;
import com.api.core.gateway.filter.BaseZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 设置响应内容
 */
@Component
public class GatewayUpdateFilter extends BaseZuulFilter {

	@Override
	public Object run() throws ZuulException {
		final SessionComponent session = sessionComponent();
		final APIType apiType = session.getApiType();
		if(apiType != null && apiType.record()) {
			// 报文保存
		}
		return null;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE_POST;
	}

	@Override
	public int filterOrder() {
		return 999;
	}

}
