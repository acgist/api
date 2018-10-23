package com.api.core.gateway.filter.post;

import org.springframework.stereotype.Component;

import com.api.core.gateway.component.SessionComponent;
import com.api.core.gateway.filter.BaseZuulFilter;
import com.api.core.gateway.response.APIResponse;
import com.netflix.zuul.exception.ZuulException;

/**
 * 签名
 */
@Component
public class SignFilter extends BaseZuulFilter {

	@Override
	public Object run() throws ZuulException {
		final SessionComponent session = sessionComponent();
		final APIResponse apiResponse = session.getResponse();
//		SignService.sign(apiResponse);
		apiResponse.setSign("111111111111");
		return null;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE_POST;
	}

	@Override
	public int filterOrder() {
		return 997;
	}

}
