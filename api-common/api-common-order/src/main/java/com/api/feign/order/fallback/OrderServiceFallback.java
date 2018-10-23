package com.api.feign.order.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.core.config.APIConstURL;
import com.api.core.order.pojo.dto.OrderDTO;
import com.api.core.order.pojo.entity.OrderEntity;
import com.api.feign.order.service.OrderService;

@Component
@RequestMapping(APIConstURL.URL_FALLBACK_SERVICE)
public class OrderServiceFallback implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceFallback.class);
	
	@Override
	public OrderDTO order(OrderEntity order) {
		LOGGER.warn("服务调用失败：创建订单，执行退款，订单号：{}", order.getOrderId());
		OrderDTO dto = new OrderDTO();
		dto.buildFail();
		dto.setEntity(order);
		return dto;
	}

}