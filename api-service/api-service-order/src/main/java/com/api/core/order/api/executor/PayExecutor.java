package com.api.core.order.api.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.api.core.gateway.executor.APIExecutor;
import com.api.core.order.gateway.request.PayRequest;
import com.api.core.order.gateway.response.PayResponse;
import com.api.core.order.pojo.dto.OrderDTO;
import com.api.core.order.pojo.entity.OrderEntity;
import com.api.core.order.service.impl.OrderServiceImpl;

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
		OrderDTO dto = orderServiceImpl.order(order);
		response.buildMessage(dto);
	}

}
