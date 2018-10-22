package com.acgist.order.api.request;

import javax.validation.constraints.NotBlank;

import com.acgist.api.request.APIRequest;

/**
 * 交易接口
 */
public class PayRequest extends APIRequest {
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "订单号不能为空")
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}