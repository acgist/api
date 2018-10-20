package com.acgist.user.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.config.APIConstURL;
import com.acgist.user.pojo.dto.AuthoDTO;
import com.acgist.user.pojo.dto.LoginDTO;
import com.acgist.user.service.UserService;

@Component
@RequestMapping(APIConstURL.URL_FALLBACK_SERVICE)
public class UserServiceFallback implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceFallback.class);

	@Override
	public AuthoDTO autho(String name) {
		LOGGER.error("服务调用失败：用户授权，用户名：{}", name);
		return null;
	}

	@Override
	public LoginDTO login(String name, String password) {
		LOGGER.error("服务调用失败：用户登陆，用户名：{}", name);
		return null;
	}
	

}
