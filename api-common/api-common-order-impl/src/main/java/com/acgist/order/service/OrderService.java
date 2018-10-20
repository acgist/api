package com.acgist.order.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.acgist.config.APIConstApplication;
import com.acgist.order.config.OrderConfig;
import com.acgist.order.fallback.OrderServiceFallback;
import com.acgist.order.service.IOrderService;

@FeignClient(value = APIConstApplication.API_SERVICE_ORDER, configuration = OrderConfig.class, fallback = OrderServiceFallback.class)
public interface OrderService extends IOrderService {

}
