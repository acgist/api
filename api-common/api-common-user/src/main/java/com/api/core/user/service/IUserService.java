package com.api.core.user.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.core.config.APIConstURL;
import com.api.core.user.config.APIConstUserURL;
import com.api.core.user.pojo.dto.AuthoDTO;
import com.api.core.user.pojo.dto.LoginDTO;

/**
 * 用户
 */
@RequestMapping(APIConstURL.URL_SERVICE)
public interface IUserService {

	/**
	 * 获取用户接口鉴权信息
	 * @param name 用户名
	 * @return 鉴权信息
	 */
	@PostMapping(APIConstUserURL.URL_USER_AUTHO)
	AuthoDTO autho(String name);

	/**
	 * 登陆
	 * @param name 用户名
	 * @param password 用户密码
	 * @return 登陆结果
	 */
	@PostMapping(APIConstUserURL.URL_USER_LOGIN)
	LoginDTO login(String name, String password);
	
}
