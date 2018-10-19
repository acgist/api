package com.acgist.service.order;

import com.acgist.pojo.dto.OrderDTO;
import com.acgist.pojo.entity.Order;

/**
 * 订单
 */
public interface IOrder {

	/**
	 * 创建订单
	 */
	OrderDTO order(Order order);
	
}
