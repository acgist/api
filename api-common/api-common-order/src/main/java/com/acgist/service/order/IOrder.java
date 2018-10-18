package com.acgist.service.order;

import com.acgist.service.order.pojo.Order;
import com.acgist.service.order.pojo.OrderResult;

/**
 * 订单
 */
public interface IOrder {

	/**
	 * 创建订单
	 */
	OrderResult order(Order order);
	
}
