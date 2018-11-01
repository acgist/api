package com.api.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.core.pojo.session.UserSession;
import com.api.core.user.config.APIConstUserURL;
import com.api.core.user.pojo.message.LoginMessage;
import com.api.feign.user.service.UserService;

@Controller
@RequestMapping(APIConstUserURL.URL_USER)
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String username, String password, HttpServletRequest request) {
		LoginMessage message = userService.login(username, password);
		if(!message.isSuccess()) {
			return "redirect:/user/login";
		}
		UserSession user = new UserSession(request);
		user.setId(message.getId());
		user.setName(message.getName());
		user.putSession();
		return "redirect:/order";
	}
	
}
