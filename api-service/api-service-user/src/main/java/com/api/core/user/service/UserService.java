package com.api.core.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.api.core.user.pojo.dto.AuthoDTO;
import com.api.core.user.pojo.dto.LoginDTO;
import com.api.core.user.service.IUserService;
import com.api.core.user.service.impl.UserServiceImpl;

@RestController
public class UserService implements IUserService {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	public AuthoDTO autho(String name) {
		return userServiceImpl.autho(name);
	}

	@Override
	public LoginDTO login(String name, String password) {
		return userServiceImpl.login(name, password);
	}

}
