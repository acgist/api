package com.acgist.api.response;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.acgist.api.API;
import com.acgist.api.APICode;
import com.acgist.api.request.APIRequest;
import com.acgist.pojo.dto.ServiceDTO;
import com.acgist.utils.APIUtils;
import com.acgist.utils.DateUtils;

/**
 * 抽象响应，使用：request.buildResponse(session).success().response();
 * 必须要设置响应状态码
 */
public class APIResponse extends API {

	private static final long serialVersionUID = 1L;

	protected String requestTime; // 请求时间
	@NotBlank(message = "响应时间不能为空")
	protected String responseTime; // 响应时间
	@NotBlank(message = "响应状态码不能为空")
	protected String responseCode; // 响应码
	protected String responseMessage; // 响应内容

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	/**
	 * 将请求数据内容设置到响应内容中
	 */
	public APIResponse valueOfRequest(APIRequest request) {
		if(request != null) {
			valueOfMap(request.data());
		}
		return this;
	}
	
	/**
	 * 设置参数
	 */
	public APIResponse valueOfMap(final Map<String, String> data) {
		if(data != null) {
			data.remove(API.PROPERTY_SIGN); // 移除签名
			APIUtils.mapToBean(this, data);
		}
		return this;
	}
	
	/**
	 * 成功
	 */
	public APIResponse buildSuccess() {
		return buildMessage(APICode.CODE_0000);
	}
	
	/**
	 * 失败
	 */
	public APIResponse buildFail() {
		return buildMessage(APICode.CODE_9999);
	}
	
	/**
	 * 设置响应信息
	 */
	public APIResponse buildMessage(APICode code) {
		return buildMessage(code.getCode(), code.getMessage());
	}
	
	/**
	 * 设置响应信息
	 */
	public APIResponse buildMessage(ServiceDTO<?> dto) {
		return buildMessage(dto.getCode(), dto.getMessage());
	}
	
	/**
	 * 设置响应信息
	 */
	public APIResponse buildMessage(APICode code, String message) {
		return buildMessage(code.getCode(), message);
	}
	
	/**
	 * 设置响应信息
	 */
	public APIResponse buildMessage(String code, String message) {
		this.responseCode = code;
		this.responseMessage = message;
		this.responseTime = DateUtils.nowDate();
		return this;
	}

	/**
	 * 获取签名后响应内容
	 */
	public Map<String, String> response() {
		return data();
	}
	
	/**
	 * 默认响应
	 */
	public static final APIResponse builder() {
		return new APIResponse();
	}
	
}
