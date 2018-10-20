package com.acgist.order.pojo.entity;

import javax.validation.constraints.NotBlank;

import com.acgist.order.api.request.OrderRequest;
import com.acgist.pojo.ValueOfRequest;
import com.acgist.pojo.entity.BaseEntity;

/**
 * 支付订单
 */
public class OrderEntity extends BaseEntity implements ValueOfRequest<OrderRequest> {

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
