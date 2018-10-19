package com.acgist.pojo.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.acgist.api.request.order.OrderRequest;
import com.acgist.pojo.ValueOfRequest;

/**
 * 支付订单
 */
public class Order extends BaseEntity implements Serializable, ValueOfRequest<OrderRequest> {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "订单号不能为空")
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void valueOfRequest(OrderRequest request) {
		this.setOrderId(request.getOrderId());
	}

}
