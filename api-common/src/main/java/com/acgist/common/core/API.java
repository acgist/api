package com.acgist.common.core;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.acgist.utils.APIUtils;
import com.acgist.utils.JSONUtils;
import com.acgist.utils.ValidatorUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 抽象请求：@JsonIgnoreProperties忽略传入的不需要的信息
 */
@JsonInclude(Include.NON_NULL)
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(ignoreUnknown = true, value = {"sign"})
public abstract class API implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PROPERTY_SIGN = "sign"; // 签名字符串

	protected String queryId; // 请求ID
	protected String reserved; // 原样返回数据
//	@JsonIgnore // 忽略序列化属性
	@NotBlank(message = "签名内容不能为空")
	protected String sign; // 签名
	
	public String getReserved() {
		return reserved;
	}
	
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**
	 * 数据格式校验
	 */
	public String verify() {
		return ValidatorUtils.verify(this);
	}

	/**
	 * 获取map数据
	 */
	public Map<String, String> data() {
		return APIUtils.beanToMap(this);
	}

	/**
	 * 返回JSON字符串
	 */
	@Override
	public String toString() {
		return JSONUtils.javaToJson(this);
	}
	
}
