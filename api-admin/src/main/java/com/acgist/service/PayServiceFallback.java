package com.acgist.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.acgist.common.service.pay.IPay;
import com.acgist.common.service.pay.pojo.Order;
import com.acgist.common.service.pay.pojo.PayResult;

@Component
public class PayServiceFallback implements IPay {

	private static final Logger LOGGER = LoggerFactory.getLogger(PayServiceFallback.class);
	
	@Override
	public PayResult pay(Order order) {
		LOGGER.warn("支付失败，执行退款，订单号：{}", order.getOrderId());
		return null;
	}

}
