package com.acgist.gateway.filter.post;

import org.springframework.stereotype.Component;

import com.acgist.gateway.api.SessionComponent;
import com.acgist.gateway.filter.BaseZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 设置响应内容
 */
@Component
public class GatewayUpdateFilter extends BaseZuulFilter {

	@Override
	public Object run() throws ZuulException {
		final SessionComponent session = sessionComponent();
//		final APIType apiType = session.getApiType();
//		if(apiType.record()) {
//			// 报文保存
//		}
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
