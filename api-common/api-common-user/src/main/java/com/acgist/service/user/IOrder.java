package com.acgist.service.user;

import com.acgist.service.user.pojo.User;
import com.acgist.service.user.pojo.UserResult;

/**
 * 订单
 */
public interface IOrder {

	/**
	 * 创建订单
	 */
	UserResult order(User order);
	
}
