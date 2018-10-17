package com.acgist.common.service.pay.pojo;

import java.io.Serializable;

import com.acgist.common.service.ServiceResult;

/**
 * 支付结果
 */
public class PayResult extends ServiceResult implements Serializable {

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
