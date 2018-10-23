package com.api.core.user.service.impl;

import org.springframework.stereotype.Service;

import com.api.core.gateway.APICode;
import com.api.core.user.pojo.dto.AuthoDTO;
import com.api.core.user.pojo.dto.LoginDTO;

@Service
public class UserServiceImpl {

//	@Autowired
//	private UserRepository userRepository;

	public AuthoDTO autho(String name) {
		if(name == null) {
			return null;
		}
		return null;
	}

	public LoginDTO login(String name, String password) {
		LoginDTO dto = new LoginDTO();
		if(name == null || password == null) {
			dto.buildMessage(APICode.CODE_2000);
		}
//		final String passwordMD5 = DigestUtils.md5Hex(password);
		return null;
	}

}
