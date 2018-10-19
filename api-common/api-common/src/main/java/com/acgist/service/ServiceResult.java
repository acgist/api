package com.acgist.service;

import java.io.Serializable;

import com.acgist.api.APICode;

/**
 * 服务调用结果：需要判断是否成功的方法返回值
 */
public class ServiceResult implements Serializable {

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
	
	public void success() {
		fail(APICode.CODE_0000);
	}
	
	public void fail() {
		fail(APICode.CODE_9999);
	}
	
	public void fail(APICode code) {
		fail(code.getCode(), code.getMessage());
	}
	
	public void fail(APICode code, String message) {
		this.code = code.getCode();
		this.message = message;
	}
	
	public void fail(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
