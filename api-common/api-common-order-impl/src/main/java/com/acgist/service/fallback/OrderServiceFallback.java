package com.acgist.service.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.acgist.service.order.IOrder;
import com.acgist.service.order.pojo.Order;
import com.acgist.service.order.pojo.OrderResult;

@Component
public class OrderServiceFallback implements IOrder {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceFallback.class);
	
	@Override
	public OrderResult order(Order order) {
		LOGGER.warn("支付失败，执行退款，订单号：{}", order.getOrderId());
		return null;
	}

}
