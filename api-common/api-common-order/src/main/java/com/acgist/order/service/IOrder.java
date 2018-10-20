package com.acgist.order.service;

import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;

/**
 * 订单
 */
public interface IOrder {

	/**
	 * 创建订单
	 */
	OrderDTO order(OrderEntity order);
	
}
