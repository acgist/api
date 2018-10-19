package com.acgist.pojo.dto;

import com.acgist.api.APICode;
import com.acgist.pojo.entity.BaseEntity;

/**
 * 需要获取是否成功的服务调用
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
