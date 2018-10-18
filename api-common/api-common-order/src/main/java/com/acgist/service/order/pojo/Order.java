package com.acgist.service.order.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.acgist.api.request.order.OrderRequest;
import com.acgist.service.ServiceParameter;
import com.acgist.service.ValueOfRequest;

/**
 * 支付订单
 */
public class Order extends ServiceParameter implements Serializable, ValueOfRequest<OrderRequest> {

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

	public void valueOfRequest(OrderRequest request) {
		this.setOrderId(request.getOrderId());
	}

}
