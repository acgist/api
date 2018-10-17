package com.acgist.api.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.common.api.executor.APIExecutor;
import com.acgist.common.api.request.pay.PayRequest;
import com.acgist.common.api.response.pay.PayResponse;
import com.acgist.common.service.pay.pojo.Order;
import com.acgist.common.service.pay.pojo.PayResult;
import com.acgist.service.impl.PayServiceImpl;

/**
 * 交易
 */
@Component
@Scope("prototype")
public class PayExecutor extends APIExecutor<PayRequest, PayResponse> {

	@Autowired
	private PayServiceImpl payServiceImpl;
	
	@Override
	public void execute() {
		Order order = new Order();
		order.valueOfRequest(request);
		PayResult result = payServiceImpl.pay(order);
		response.message(result);
	}
	
}
