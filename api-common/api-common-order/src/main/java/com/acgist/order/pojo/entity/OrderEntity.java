package com.acgist.order.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.acgist.order.api.request.PayRequest;
import com.acgist.pojo.entity.BaseEntity;

/**
 * 支付订单
 */
@Entity
@Table(name = "tb_order")
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class OrderEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "订单号不能为空")
	private String orderId;

	@Column(nullable = false, length = 100)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void valueOfPayRequest(PayRequest request) {
		this.setOrderId(request.getOrderId());
	}

}
