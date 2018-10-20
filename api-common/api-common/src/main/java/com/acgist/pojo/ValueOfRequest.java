package com.acgist.pojo;

import com.acgist.api.request.APIRequest;

/**
 * 需要将请求转化为数据库实体时实现该接口
 */
public interface ValueOfRequest<T extends APIRequest> {

	void valueOfRequest(T request);
	
}
