package com.acgist.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.acgist.order.config.APIConstOrderURL;

@Controller
public class IndexController {

	@GetMapping("/")
	public String indexs() {
		return "redirect:" + APIConstOrderURL.URL_ORDER;
	}
	
}
