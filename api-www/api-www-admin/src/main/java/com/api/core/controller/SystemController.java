package com.api.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	@GetMapping
	public String index() {
		return "/system/index";
	}
	
}
