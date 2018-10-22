package com.acgist.gateway.filter.pre;

import org.springframework.stereotype.Component;

import com.acgist.gateway.api.APIType;
import com.acgist.gateway.api.SessionComponent;
import com.acgist.gateway.filter.BaseZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 报文保存
 */
@Component
public class GatewaySaveFilter extends BaseZuulFilter {

	@Override
	public boolean shouldFilter() {
		return permissions();
	}
	
	@Override
	public Object run() throws ZuulException {
		final SessionComponent session = sessionComponent();
		final APIType apiType = session.getApiType();
		if(apiType.record()) {
			// 报文保存
		}
		return null;
	}
	
	@Override
	public int filterOrder() {
		return 130;
	}
	
	@Override
	public String filterType() {
		return FILTER_TYPE_PRE;
	}

}
