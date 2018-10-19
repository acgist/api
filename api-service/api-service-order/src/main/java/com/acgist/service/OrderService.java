package com.acgist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acgist.pojo.dto.OrderDTO;
import com.acgist.pojo.entity.Order;
import com.acgist.service.impl.OrderServiceImpl;
import com.acgist.service.order.IOrderService;

@RestController
public class OrderService implements IOrderService {

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@Override
	public OrderDTO order(@RequestBody Order order) {
		return orderServiceImpl.order(order);
	}

}
