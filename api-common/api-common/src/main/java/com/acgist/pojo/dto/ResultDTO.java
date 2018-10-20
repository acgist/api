package com.acgist.pojo.dto;

import com.acgist.api.APICode;

/**
 * DTO：需要知道调用结果的服务使用
 */
public class ResultDTO extends BaseDTO {

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

	public ResultDTO buildSuccess() {
		return buildMessage(APICode.CODE_0000);
	}

	public ResultDTO buildFail() {
		return buildMessage(APICode.CODE_9999);
	}

	public ResultDTO buildMessage(APICode code) {
		return buildMessage(code.getCode(), code.getMessage());
	}

	public ResultDTO buildMessage(APICode code, String message) {
		return buildMessage(code.getCode(), message);
	}

	public ResultDTO buildMessage(String code, String message) {
		this.code = code;
		this.message = message;
		return this;
	}

}
