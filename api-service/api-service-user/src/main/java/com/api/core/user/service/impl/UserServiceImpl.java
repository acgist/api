package com.api.core.user.service.impl;

import org.springframework.stereotype.Service;

import com.api.core.gateway.APICode;
import com.api.core.user.pojo.message.AuthoMessage;
import com.api.core.user.pojo.message.LoginMessage;

@Service
public class UserServiceImpl {

//	@Autowired
//	private UserRepository userRepository;

	public AuthoMessage autho(String name) {
		if(name == null) {
			return null;
		}
		return null;
	}

	public LoginMessage login(String name, String password) {
		LoginMessage message = new LoginMessage();
		if(name == null || password == null) {
			message.buildMessage(APICode.CODE_2000);
		}
//		final String passwordMD5 = DigestUtils.md5Hex(password);
		return null;
	}

}
