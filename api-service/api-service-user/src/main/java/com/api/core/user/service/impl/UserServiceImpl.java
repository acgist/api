package com.api.core.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.core.gateway.APICode;
import com.api.core.user.pojo.message.AuthoMessage;
import com.api.core.user.pojo.message.LoginMessage;
import com.api.data.user.pojo.entity.UserEntity;
import com.api.data.user.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

	public AuthoMessage autho(String name) {
		if(name == null) {
			return null;
		}
		UserEntity user = userRepository.findName(name);
		if(user == null) {
			return null;
		}
		AuthoMessage message = new AuthoMessage();
		message.setName(name);
		message.setPubilcKey(user.getPubilcKey());
		message.setPrivateKey(user.getPrivateKey());
		return message;
	}

	public LoginMessage login(String name, String password) {
		LoginMessage message = new LoginMessage();
		if(name == null || password == null) {
			message.buildMessage(APICode.CODE_2000);
			return message;
		}
		UserEntity user = userRepository.findName(name);
		if(user == null) {
			message.buildMessage(APICode.CODE_9999, "不存在的用户");
			return message;
		}
		if(!StringUtils.equals(user.getPassword(), password)) {
			message.buildMessage(APICode.CODE_2000);
			return message;
		}
		message.buildSuccess();
		message.setId(user.getId());
		message.setName(user.getName());
		return message;
	}

}
