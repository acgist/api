package com.acgist.gateway.api.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.api.executor.APIExecutor;
import com.acgist.order.api.request.OrderRequest;
import com.acgist.order.api.response.OrderResponse;
import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.order.service.OrderService;

/**
 * 创建订单
 */
@Component
@Scope("prototype")
public class OrderExecutor extends APIExecutor<OrderRequest, OrderResponse> {

	@Autowired
	private OrderService orderService;
	
	@Override
	public void execute() {
		OrderEntity order = new OrderEntity();
		order.valueOfRequest(request);
		OrderDTO dto = orderService.order(order);
		response.buildMessage(dto);
	}
	
}
