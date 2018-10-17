package com.acgist.common.api.request.pay;

import javax.validation.constraints.NotBlank;

import com.acgist.common.api.request.APIRequest;

/**
 * 交易请求
 */
public class PayRequest extends APIRequest {
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "交易订单号不能为空")
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
