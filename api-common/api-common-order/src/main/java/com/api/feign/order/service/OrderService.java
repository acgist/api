package com.api.feign.order.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.api.core.config.APIConstApplication;
import com.api.core.order.service.IOrderService;
import com.api.feign.order.config.OrderConfig;
import com.api.feign.order.fallback.OrderServiceFallback;

@FeignClient(value = APIConstApplication.API_SERVICE_ORDER, configuration = OrderConfig.class, fallback = OrderServiceFallback.class)
public interface OrderService extends IOrderService {

}
