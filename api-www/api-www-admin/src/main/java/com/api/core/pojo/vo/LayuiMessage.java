package com.api.core.pojo.vo;

import java.io.Serializable;

import com.api.core.gateway.APICode;

/**
 * layui消息
 */
public class LayuiMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public static final LayuiMessage build(APICode code) {
		LayuiMessage message = new LayuiMessage();
		message.code = code.getCode();
		message.message = code.getMessage();
		return message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
