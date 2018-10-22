package com.acgist.gateway.filter.pre;

import org.springframework.stereotype.Component;

import com.acgist.api.APICode;
import com.acgist.gateway.filter.BaseZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 签名验证，验证请求所有数据，而不是实体数据
 */
@Component
public class SignVerifyFilter extends BaseZuulFilter {

	@Override
	public boolean shouldFilter() {
		return permissions();
	}
	
	@Override
	public Object run() throws ZuulException {
//		final SessionComponent session = sessionComponent();
//		final String json = session.getJson();
//		final Map<String, String> data = JSONUtils.jsonToMap(json);
//		if(SignService.verify(data)) {
//			return null;
//		}
		error(APICode.CODE_3001);
		return null;
	}

	@Override
	public int filterOrder() {
		return 110;
	}
	
	@Override
	public String filterType() {
		return FILTER_TYPE_PRE;
	}

}
