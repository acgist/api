package com.acgist.service.impl;

import org.springframework.stereotype.Service;

import com.acgist.api.APICode;
import com.acgist.pojo.dto.OrderDTO;
import com.acgist.pojo.entity.Order;
import com.acgist.service.order.IOrder;

@Service
public class OrderServiceImpl implements IOrder {

	@Override
	public OrderDTO order(Order order) {
		String orderId = order.getOrderId();
		OrderDTO dto = new OrderDTO();
		if("fail".equals(orderId)) {
			dto.fail(APICode.CODE_9999, "创建订单失败");
		} else if ("exception".equals(orderId)) {
			throw new RuntimeException();
		} else {
			dto.success();
		}
		dto.setEntity(order);
		return dto;
	}

}
