package com.acgist.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.order.repository.OrderRepository;
import com.acgist.service.APIEntityService;

@Service
public class OrderServiceImpl implements APIEntityService {

	@Autowired
	private OrderRepository orderRepository;
	
	public OrderDTO order(OrderEntity order) {
		OrderDTO dto = new OrderDTO();
		if(!verifyEntity(order, dto)) {
			return dto;
		}
		orderRepository.save(order);
		dto.buildSuccess();
		dto.setEntity(order);
		return dto;
	}

}
