package com.acgist.order.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.config.APIConstURL;
import com.acgist.order.config.APIConstOrderURL;
import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;

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
