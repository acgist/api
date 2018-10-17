package com.acgist.service.impl;

import org.springframework.stereotype.Service;

import com.acgist.common.core.APICode;
import com.acgist.common.service.pay.IPay;
import com.acgist.common.service.pay.pojo.Order;
import com.acgist.common.service.pay.pojo.PayResult;

@Service
public class PayServiceImpl implements IPay {

	@Override
	public PayResult pay(Order order) {
		String orderId = order.getOrderId();
		PayResult result = new PayResult();
		if("fail".equals(orderId)) {
			result.message(APICode.CODE_9999, "交易失败");
		} else if ("exception".equals(orderId)) {
			throw new RuntimeException();
		} else {
			result.success();
		}
		result.setId(order.getId());
		result.setOrderId(order.getOrderId());
		return result;
	}

}
