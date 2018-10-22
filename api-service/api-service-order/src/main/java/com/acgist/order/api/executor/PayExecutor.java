package com.acgist.order.api.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.api.executor.APIExecutor;
import com.acgist.order.api.request.PayRequest;
import com.acgist.order.api.response.PayResponse;
import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.order.service.impl.OrderServiceImpl;

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
