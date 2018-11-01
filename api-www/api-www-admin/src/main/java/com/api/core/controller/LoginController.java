package com.api.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.core.config.APIConstAdminURL;

@Controller
public class LoginController {

	@GetMapping(APIConstAdminURL.URL_LOGIN)
	public String loginGet() {
		return "/login";
	}
	
	@PostMapping(APIConstAdminURL.URL_LOGIN)
	public String loginPost() {
		return "/login";
	}
	
	@GetMapping(APIConstAdminURL.URL_LOGOUT)
	public String logout() {
		return "/login";
	}

}
