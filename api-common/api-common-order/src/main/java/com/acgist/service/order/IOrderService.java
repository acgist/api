package com.acgist.service.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.config.APIConstURL;
import com.acgist.pojo.dto.OrderDTO;
import com.acgist.pojo.entity.Order;

/**
 * 订单服务，不能继承IOrder，feign不支持多层继承
 */
@RequestMapping(APIConstURL.URL_SERVICE)
public interface IOrderService {

	@PostMapping(APIConstURL.URL_ORDER)
	OrderDTO order(@RequestBody Order order);
	
}
