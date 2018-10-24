package com.api.core.order.gateway.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.api.core.gateway.executor.APIExecutor;
import com.api.core.order.gateway.request.PayRequest;
import com.api.core.order.gateway.response.PayResponse;
import com.api.core.order.pojo.message.OrderMessage;
import com.api.core.order.service.impl.OrderServiceImpl;
import com.api.data.order.pojo.entity.OrderEntity;

/**
 * 订单支付
 */
@Component
@Scope("prototype")
public class PayExecutor extends APIExecutor<PayRequest, PayResponse> {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Override
	public void execute() {
		OrderEntity order = new OrderEntity();
		order.valueOfPayRequest(request);
		OrderMessage message = orderServiceImpl.order(order);
		response.buildMessage(message);
	}

}
