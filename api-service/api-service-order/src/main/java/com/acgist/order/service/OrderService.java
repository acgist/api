package com.acgist.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.order.service.IOrderService;
import com.acgist.order.service.impl.OrderServiceImpl;

@RestController
public class OrderService implements IOrderService {

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@Override
	public OrderDTO order(@RequestBody OrderEntity order) {
		return orderServiceImpl.order(order);
	}

}
