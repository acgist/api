package com.acgist.order.api.response;

import com.acgist.api.response.APIResponse;

/**
 * 创建订单响应
 */
public class PayResponse extends APIResponse {

	private static final long serialVersionUID = 1L;

	private String orderId; // 订单号

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
