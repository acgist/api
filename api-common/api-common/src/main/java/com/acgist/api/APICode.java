package com.acgist.api;

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
	CODE_4404("4404", "服务不存在"),
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
	
	public static final APICode valueOfHTTPCode(int code) {
		return valueOfCode(RESPONSE_ERROR + code);
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
