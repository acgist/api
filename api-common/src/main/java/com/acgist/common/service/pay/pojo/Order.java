package com.acgist.common.service.pay.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.acgist.common.api.request.pay.PayRequest;
import com.acgist.common.service.ServiceData;
import com.acgist.common.service.ValueOfRequest;

/**
 * 支付订单
 */
public class Order extends ServiceData implements Serializable, ValueOfRequest<PayRequest> {

	private static final long serialVersionUID = 1L;

	private String id;
	@NotBlank(message = "订单号不能为空")
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

	public void valueOfRequest(PayRequest request) {
		this.setOrderId(request.getOrderId());
	}
	
}
