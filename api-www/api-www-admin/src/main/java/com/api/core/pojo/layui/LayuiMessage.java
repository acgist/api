package com.api.core.pojo.layui;

import java.io.Serializable;

import com.api.core.gateway.APICode;

/**
 * layui - 消息
 */
public class LayuiMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public static final LayuiMessage success() {
		return build(APICode.CODE_0000);
	}
	
	public static final LayuiMessage build(APICode code) {
		return build(code.getCode(), code.getMessage());
	}
	
	public static final LayuiMessage build(String code, String message) {
		LayuiMessage layuiMessage = new LayuiMessage();
		layuiMessage.code = code;
		layuiMessage.message = message;
		return layuiMessage;
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
