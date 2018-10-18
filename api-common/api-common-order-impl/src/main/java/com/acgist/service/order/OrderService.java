package com.acgist.service.order;

import org.springframework.cloud.openfeign.FeignClient;

import com.acgist.api.APIConstApplication;
import com.acgist.service.config.OrderConfig;
import com.acgist.service.fallback.OrderServiceFallback;

@FeignClient(value = APIConstApplication.API_SERVICE_ORDER, configuration = OrderConfig.class, fallback = OrderServiceFallback.class)
public interface OrderService extends IOrderService {

}
