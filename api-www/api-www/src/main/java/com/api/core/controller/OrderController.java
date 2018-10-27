package com.api.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.core.order.config.APIConstOrderURL;
import com.api.core.order.pojo.message.OrderMessage;
import com.api.data.order.pojo.entity.OrderEntity;
import com.api.feign.order.service.OrderService;

/**
 * 订单管理
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(APIConstOrderURL.URL_ORDER)
	public String order() {
		return "/order/index";
	}
	
	@PostMapping(APIConstOrderURL.URL_ORDER)
	public String order(OrderEntity order, ModelMap model) {
		OrderMessage message = orderService.order(order);
		model.put("message", message);
		return "/order/index";
	}
	
}
