package com.api.core.pojo.layui;

import java.io.Serializable;

import com.api.core.gateway.APICode;
import com.api.core.gateway.response.APIResponse;

/**
 * layui - 消息
 */
public class LayuiMessage extends APIResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final LayuiMessage success() {
		return build(APICode.CODE_0000);
	}
	
	public static final LayuiMessage build(APICode code) {
		return build(code.getCode(), code.getMessage());
	}
	
	public static final LayuiMessage build(String code, String message) {
		LayuiMessage layuiMessage = new LayuiMessage();
		layuiMessage.buildMessage(code, message);
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
