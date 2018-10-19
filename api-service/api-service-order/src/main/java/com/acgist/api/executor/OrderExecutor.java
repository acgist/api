package com.acgist.api.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.api.request.order.OrderRequest;
import com.acgist.api.response.order.OrderResponse;
import com.acgist.service.impl.OrderServiceImpl;
import com.acgist.service.order.pojo.Order;
import com.acgist.service.order.pojo.OrderResult;

/**
 * 创建订单
 */
@Component
@Scope("prototype")
public class OrderExecutor extends APIExecutor<OrderRequest, OrderResponse> {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Override
	public void execute() {
		Order order = new Order();
		order.valueOfRequest(request);
		OrderResult result = orderServiceImpl.order(order);
		response.fail(result);
	}
	
}
