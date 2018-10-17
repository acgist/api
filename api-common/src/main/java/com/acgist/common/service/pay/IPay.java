package com.acgist.common.service.pay;

import com.acgist.common.service.pay.pojo.Order;
import com.acgist.common.service.pay.pojo.PayResult;

/**
 * 交易
 */
public interface IPay {

	/**
	 * 交易
	 */
	PayResult pay(Order order);
	
}
