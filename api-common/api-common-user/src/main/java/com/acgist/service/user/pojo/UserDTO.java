package com.acgist.service.user.pojo;

import java.io.Serializable;

import com.acgist.pojo.dto.BaseDTO;

/**
 * 结果
 */
public class UserDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String orderId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
