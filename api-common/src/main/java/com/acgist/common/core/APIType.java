package com.acgist.common.core;

import javax.servlet.http.HttpServletRequest;

import com.acgist.common.api.request.APIRequest;
import com.acgist.common.api.request.pay.DrawbackRequest;
import com.acgist.common.api.request.pay.PayRequest;
import com.acgist.common.api.request.pay.QueryRequest;
import com.acgist.common.exception.ErrorCodeException;

/**
 * 接口类型
 */
public enum APIType {

	PAY("交易", APIConstURL.URL_GATEWAY_PAY, PayRequest.class, true),
	PAY_QUERY("交易查询", APIConstURL.URL_GATEWAY_PAY_QUERY, QueryRequest.class),
	PAY_DRAWBACK("交易退款", APIConstURL.URL_GATEWAY_PAY_DRAWBACK, DrawbackRequest.class, true);

	private boolean record; // 是否记录
	private String typeName; // 接口名称
	private String requestURL; // 请求地址
	private Class<APIRequest> requestClazz; // 请求类型

	private <T extends APIRequest> APIType(String typeName, String requestURL, Class<T> requestClazz) {
		this(typeName, requestURL, requestClazz, false);
	}

	// TODO 优化泛型
	@SuppressWarnings("unchecked")
	private <T extends APIRequest> APIType(String typeName, String requestURL, Class<T> requestClazz, boolean record) {
		this.record = record;
		this.typeName = typeName;
		this.requestURL = requestURL;
		this.requestClazz = (Class<APIRequest>) requestClazz;
	}

	public static final APIType valueOfRequest(HttpServletRequest request) {
		final String requestURL = request.getServletPath();
		for (APIType api : APIType.values()) {
			if (api.requestURL.equals(requestURL)) {
				return api;
			}
		}
		throw new ErrorCodeException(APICode.CODE_1000);
	}
	
	public boolean record() {
		return record;
	}
	
	public String typeName() {
		return typeName;
	}

	public String requestURL() {
		return requestURL;
	}

	public Class<APIRequest> reqeustClazz() {
		return requestClazz;
	}

}
