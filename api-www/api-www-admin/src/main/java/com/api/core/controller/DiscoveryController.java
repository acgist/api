package com.api.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.core.service.DiscoveryService;

/**
 * 服务工具
 */
@Controller
@RequestMapping("/discovery")
public class DiscoveryController {

	@Autowired
	private DiscoveryService discoveryService;
	
	@GetMapping
	public String index(ModelMap model) {
		model.addAttribute("services", discoveryService.list());
		return "/discovery/index";
	}
	
}
