package com.acgist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.common.core.APIConstURL;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "redirect:" + APIConstURL.URL_ADMIN_PAY;
	}
	
}
