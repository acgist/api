package com.acgist.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.acgist.config.APIConstURL;
import com.acgist.order.pojo.dto.OrderDTO;
import com.acgist.order.pojo.entity.OrderEntity;
import com.acgist.order.service.OrderService;

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
	public String order(OrderEntity order, ModelMap model) {
		OrderDTO dto = orderService.order(order);
		model.put("dto", dto);
		return "/order/index";
	}
	
}
