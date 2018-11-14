package com.api.core.pojo.message;

import com.api.core.gateway.APICode;

/**
 * message - 服务间通信，返回结果
 */
public class ResultMessage extends BaseMessage {

	private static final long serialVersionUID = 1L;

	private static final String CODE_SUCCESS = APICode.CODE_0000.getCode();

	protected String code;
	protected String message;

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

	/**
	 * 是否成功
	 */
	public boolean isSuccess() {
		return CODE_SUCCESS.equals(this.getCode());
	}

	public ResultMessage buildSuccess() {
		return buildMessage(APICode.CODE_0000);
	}

	public ResultMessage buildFail() {
		return buildMessage(APICode.CODE_9999);
	}

	public ResultMessage buildMessage(APICode code) {
		return buildMessage(code.getCode(), code.getMessage());
	}

	public ResultMessage buildMessage(APICode code, String message) {
		return buildMessage(code.getCode(), message);
	}

	public ResultMessage buildMessage(String code, String message) {
		this.code = code;
		this.message = message;
		return this;
	}

}
