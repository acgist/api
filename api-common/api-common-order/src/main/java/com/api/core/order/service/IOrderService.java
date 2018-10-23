package com.api.core.order.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.core.config.APIConstURL;
import com.api.core.order.config.APIConstOrderURL;
import com.api.core.order.pojo.dto.OrderDTO;
import com.api.core.order.pojo.entity.OrderEntity;

/**
 * 订单服务
 */
@RequestMapping(APIConstURL.URL_SERVICE)
public interface IOrderService {

	/**
	 * 创建订单
	 */
	@PostMapping(APIConstOrderURL.URL_ORDER)
	OrderDTO order(@RequestBody OrderEntity order);

}
