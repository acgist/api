package com.acgist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acgist.common.service.pay.IPayService;
import com.acgist.common.service.pay.pojo.Order;
import com.acgist.common.service.pay.pojo.PayResult;
import com.acgist.service.impl.PayServiceImpl;

@RestController
public class PayService implements IPayService {

	@Autowired
	private PayServiceImpl payServiceImpl;

	@Override
	public PayResult pay(@RequestBody Order order) {
		return payServiceImpl.pay(order);
	}

}
