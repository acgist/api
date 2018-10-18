package com.acgist.service;

import com.acgist.api.request.APIRequest;

/**
 * 通过接口生成实体
 */
public interface ValueOfRequest<T extends APIRequest> {

	void valueOfRequest(T request);
	
}
