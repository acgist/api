package com.api.core.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.core.pojo.session.UserSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String username, String password, HttpServletRequest request) {
		UserSession user = new UserSession(request);
		user.setName(username);
		user.putSession();
		return "redirect:/order";
	}
	
}
