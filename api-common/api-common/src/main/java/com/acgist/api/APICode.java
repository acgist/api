package com.acgist.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.acgist.exception.ErrorCodeException;

/**
 * 错误状态码
 * 0000=成功
 * 9999=未知错误
 * 1xxx=系统错误
 * 2xxx=业务错误
 * 3xxx=数据错误
 * 4xxx=系统异常（主要处理服务器错误）
 */
public enum APICode {

	CODE_0000("0000", "成功"),
	
	CODE_1000("1000", "未知接口"),
	CODE_1001("1001", "该连接已有请求在处理中"),
	CODE_1002("1002", "服务不可用"),
	
	CODE_2000("2000", "用户名和用户密码不匹配"),
	
	CODE_3000("3000", "数据格式错误"),
	CODE_3001("3001", "验签失败"),
	
	CODE_4400("4400", "请求无效"),
	CODE_4404("4404", "资源不存在"),
	CODE_4405("4405", "服务不支持"),
	
	CODE_9999("9999", "未知错误");
	
	private String code;
	private String message;

	private static final String RESPONSE_ERROR = "4";
	
	private APICode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static final APICode valueOfCode(String code) {
		for (APICode apiCode : APICode.values()) {
			if(apiCode.code.equals(code)) {
				return apiCode;
			}
		}
		return CODE_9999;
	}
	
	public static final APICode valueOfStatus(int statusCode) {
		return valueOfCode(RESPONSE_ERROR + statusCode);
	}
	
	public static final APICode valueOfThrowable(final Throwable e, HttpServletResponse response) {
		if(e == null) {
			return APICode.CODE_9999;
		}
		Throwable t = e;
		while(t.getCause() != null) {
			t = t.getCause();
		}
		APICode code;
		if (t instanceof ErrorCodeException) {
			ErrorCodeException exception = (ErrorCodeException) t;
			code = APICode.valueOfCode(exception.getErrorCode());
		} else if (t instanceof HttpRequestMethodNotSupportedException) {
			code = APICode.CODE_4405;
		} else if (t instanceof HttpMessageNotReadableException) {
			code = APICode.CODE_4400;
		} else {
			if(response == null) {
				code = APICode.CODE_9999;
			} else {
				code = APICode.valueOfStatus(response.getStatus());
			}
		}
		return code;
	}
	
	public static final String message(APICode code, Throwable e) {
		if(e == null) {
			return code.getMessage();
		}
		String message = null;
		if (e instanceof ErrorCodeException) {
			message = e.getMessage();
		}
		return message(code, message);
	}
	
	public static final String message(APICode code, String message) {
		if(message == null || message.isEmpty()) {
			message = code.getMessage();
		}
		return message;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
