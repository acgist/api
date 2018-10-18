package com.acgist.service.impl;

import org.springframework.stereotype.Service;

import com.acgist.api.APICode;
import com.acgist.service.order.IOrder;
import com.acgist.service.order.pojo.Order;
import com.acgist.service.order.pojo.OrderResult;

@Service
public class OrderServiceImpl implements IOrder {

	@Override
	public OrderResult order(Order order) {
		String orderId = order.getOrderId();
		OrderResult result = new OrderResult();
		if("fail".equals(orderId)) {
			result.message(APICode.CODE_9999, "创建订单失败");
		} else if ("exception".equals(orderId)) {
			throw new RuntimeException();
		} else {
			result.success();
		}
		result.setId(order.getId());
		result.setOrderId(order.getOrderId());
		return result;
	}

}
