package com.acgist.pojo.dto;

import com.acgist.api.APICode;
import com.acgist.pojo.entity.BaseEntity;

/**
 * DTO：需要知道调用结果的服务使用
 */
public class ServiceDTO<T extends BaseEntity> extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private static final String CODE_SUCCESS = APICode.CODE_0000.getCode();

	protected T entity;
	protected String code;
	protected String message;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
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

	/**
	 * 是否成功
	 */
	public boolean isSuccess() {
		return CODE_SUCCESS.equals(this.getCode());
	}

	public void buildSuccess() {
		buildMessage(APICode.CODE_0000);
	}

	public void buildFail() {
		buildMessage(APICode.CODE_9999);
	}

	public void buildMessage(APICode code) {
		buildMessage(code.getCode(), code.getMessage());
	}

	public void buildMessage(APICode code, String message) {
		buildMessage(code.getCode(), message);
	}

	public void buildMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
