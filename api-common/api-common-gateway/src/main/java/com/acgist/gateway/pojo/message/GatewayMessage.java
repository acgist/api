package com.acgist.gateway.pojo.message;

import com.acgist.pojo.message.BaseMessage;

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
