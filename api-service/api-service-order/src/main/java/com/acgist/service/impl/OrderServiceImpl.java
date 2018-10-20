package com.acgist.service.impl;

import org.springframework.stereotype.Service;

import com.acgist.api.APICode;
import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.order.service.IOrder;

@Service
public class OrderServiceImpl implements IOrder {

	@Override
	public OrderDTO order(OrderEntity order) {
		String orderId = order.getOrderId();
		OrderDTO dto = new OrderDTO();
		if("fail".equals(orderId)) {
			dto.buildMessage(APICode.CODE_9999, "创建订单失败");
		} else if ("exception".equals(orderId)) {
			throw new RuntimeException();
		} else {
			dto.buildSuccess();
		}
		dto.setEntity(order);
		return dto;
	}

}
