package com.acgist.gateway.filter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.acgist.api.APICode;
import com.acgist.api.request.APIRequest;
import com.acgist.api.response.APIResponse;
import com.acgist.gateway.api.APIType;
import com.acgist.gateway.api.SessionComponent;
import com.acgist.service.UniqueNumberService;
import com.acgist.utils.JSONUtils;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 请求数据打包
 */
@Component
public class PackageFilter extends BaseZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(PackageFilter.class);
	
	@Autowired
	private UniqueNumberService uniqueNumberService;
	
	@Override
	public Object run() throws ZuulException {
		final RequestContext context = context();
        final HttpServletRequest request = context.getRequest();
		final String requestJSON = requestJSON(request);
		if(requestJSON.isEmpty()) {
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			context.setResponseBody(APIResponse.builder().buildMessage(APICode.CODE_3000, "请求数据不能为空").response());
			return null;
		}
		final String queryId = uniqueNumberService.buildId();
		final APIType apiType = APIType.valueOfRequest(request);
		final SessionComponent session = SessionComponent.newInstance(queryId, context);
		final APIRequest apiRequest = JSONUtils.jsonToJava(requestJSON, apiType.reqeustClazz());
		session.setApiType(apiType);
		session.setJson(requestJSON);
		session.setRequest(apiRequest);
		return null;
	}
	
	private String requestJSON(HttpServletRequest request) {
		final StringBuffer builder = new StringBuffer();
		try {
			String tmp = null;
			final InputStream input = request.getInputStream();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			while((tmp = reader.readLine()) != null) {
				builder.append(tmp);
			}
		} catch (Exception e) {
			LOGGER.error("获取请求参数异常", e);
		}
		return builder.toString();
	}
	
	@Override
	public int filterOrder() {
		return 10000;
	}
	
	@Override
	public String filterType() {
		return FILTER_TYPE_PRE;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

}
