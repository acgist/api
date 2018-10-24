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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
