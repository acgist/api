package com.acgist.service.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.api.APICode;
import com.acgist.api.APIConstURL;
import com.acgist.service.order.OrderService;
import com.acgist.service.order.pojo.Order;
import com.acgist.service.order.pojo.OrderResult;

@Component
@RequestMapping(APIConstURL.URL_FALLBACK_SERVICE)
public class OrderServiceFallback implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceFallback.class);
	
	@Override
	public OrderResult order(Order order) {
		LOGGER.warn("支付失败，执行退款，订单号：{}", order.getOrderId());
		OrderResult result = new OrderResult();
		result.setOrderId(order.getOrderId());
		result.fail(APICode.CODE_9999);
		return result;
	}

}
