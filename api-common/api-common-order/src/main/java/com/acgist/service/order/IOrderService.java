package com.acgist.service.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.api.APIConstURL;
import com.acgist.service.order.pojo.Order;
import com.acgist.service.order.pojo.OrderResult;

/**
 * 订单服务，不能继承IOrder，feign不支持多层继承
 */
@RequestMapping(APIConstURL.URL_SERVICE)
public interface IOrderService {

	@PostMapping(APIConstURL.URL_ORDER)
	OrderResult order(@RequestBody Order order);
	
}
