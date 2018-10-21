package com.acgist.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acgist.api.APICode;
import com.acgist.user.pojo.dto.AuthoDTO;
import com.acgist.user.pojo.dto.LoginDTO;
import com.acgist.user.repository.UserRepository;
import com.acgist.user.service.IUser;

@Service
public class UserServiceImpl implements IUser {

	@Autowired
	private UserRepository userRepository;

	@Override
	public AuthoDTO autho(String name) {
		if(name == null) {
			return null;
		}
		return null;
	}

	@Override
	public LoginDTO login(String name, String password) {
		LoginDTO dto = new LoginDTO();
		if(name == null || password == null) {
			dto.buildMessage(APICode.CODE_2000);
		}
//		final String passwordMD5 = DigestUtils.md5Hex(password);
		return null;
	}

}
