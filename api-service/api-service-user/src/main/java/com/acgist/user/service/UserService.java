package com.acgist.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.acgist.user.pojo.dto.AuthoDTO;
import com.acgist.user.pojo.dto.LoginDTO;
import com.acgist.user.service.impl.UserServiceImpl;

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
