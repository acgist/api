package com.api.core.service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.data.repository.UserRepository;
import com.api.data.service.EntityService;
import com.api.data.user.pojo.entity.UserEntity;
import com.api.utils.CAUtils;

@Service
public class UserService extends EntityService<UserEntity> {

	@Autowired
	public UserService(UserRepository repository) {
		super(repository);
	}

	/**
	 * 证书：应该生成两对，出于简单这里用一对
	 */
	public Map<String, String> cert() {
		Map<String, String> data =	new HashMap<>(2);
		KeyPair keyPair = CAUtils.keyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		data.put("publicKey", CAUtils.keyToString(publicKey));
		data.put("privateKey", CAUtils.keyToString(privateKey));
		return data;
	}
	
}
