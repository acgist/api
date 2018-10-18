package com.acgist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.acgist.api.APIConstURL;
import com.acgist.service.order.OrderService;
import com.acgist.service.order.pojo.Order;
import com.acgist.service.order.pojo.OrderResult;

/**
 * 订单管理
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(APIConstURL.URL_ORDER)
	public String order() {
		return "/order/index";
	}
	
	@PostMapping(APIConstURL.URL_ORDER)
	public String order(Order order, ModelMap model) {
		OrderResult result = orderService.order(order);
		model.put("result", result);
		return "/order/index";
	}
	
}
