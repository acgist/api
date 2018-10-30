package com.api.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping()
	public String login() {
		return "/login/index";
	}
	
	@PostMapping()
	public String login(String name, String password) {
		return "/login/index";
	}

}
