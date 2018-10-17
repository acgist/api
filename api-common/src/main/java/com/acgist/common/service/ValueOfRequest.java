package com.acgist.common.service;

import com.acgist.common.api.request.APIRequest;

/**
 * 通过接口生成实体
 */
public interface ValueOfRequest<T extends APIRequest> {

	void valueOfRequest(T request);
	
}
