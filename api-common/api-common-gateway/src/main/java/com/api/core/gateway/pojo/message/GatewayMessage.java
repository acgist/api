package com.api.core.gateway.pojo.message;

import com.api.core.pojo.message.BaseMessage;

/**
 * 网关消息
 */
public class GatewayMessage extends BaseMessage {

	private static final long serialVersionUID = 1L;

	private String type;
	private String code;
	private String queryId;
	private String content;
	
}
