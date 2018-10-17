package com.acgist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.common.core.APIConstURL;
import com.acgist.common.service.pay.pojo.Order;
import com.acgist.common.service.pay.pojo.PayResult;
import com.acgist.service.PayService;

/**
 * 交易
 */
@Controller
@RequestMapping(APIConstURL.URL_ADMIN)
public class PayController {

	@Autowired
	private PayService payService;
	
	@GetMapping(APIConstURL.URL_PAY)
	public String pay() {
		return "/pay/index";
	}
	
	@PostMapping("/pay")
	public String pay(Order order, ModelMap model) {
		PayResult result = payService.pay(order);
		model.put("result", result);
		return "/pay/index";
	}
	
}
