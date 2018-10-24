package com.api.core.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.core.order.pojo.entity.OrderEntity;
import com.api.core.order.pojo.message.OrderMessage;
import com.api.core.order.service.impl.OrderServiceImpl;

@RestController
public class OrderService implements IOrderService {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Override
	public OrderMessage order(@RequestBody OrderEntity order) {
		return orderServiceImpl.order(order);
	}

}
