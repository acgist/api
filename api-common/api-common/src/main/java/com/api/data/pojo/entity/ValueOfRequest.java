package com.api.data.pojo.entity;

import com.api.core.gateway.request.APIRequest;

/**
 * 请求转换为实体
 */
public interface ValueOfRequest<T extends APIRequest> {

	void valueOfRequest(T request);
	
}
