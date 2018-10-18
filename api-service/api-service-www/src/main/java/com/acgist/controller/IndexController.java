package com.acgist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.acgist.api.APIConstURL;

@Controller
public class IndexController {

	@GetMapping("/")
	public String indexs() {
		return "redirect:" + APIConstURL.URL_ORDER;
	}
	
}
