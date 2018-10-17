package com.acgist.common.exception;

import com.acgist.common.core.APICode;

/**
 * 错误状态码
 */
public class ErrorCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorCodeException() {
	}

	public ErrorCodeException(APICode code) {
		this(code.getCode(), code.getMessage());
	}
	
	public ErrorCodeException(APICode code, String message) {
		this(code.getCode(), message);
	}
	
	public ErrorCodeException(String code, String message) {
		super(message);
		this.errorCode = code;
	}

	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
