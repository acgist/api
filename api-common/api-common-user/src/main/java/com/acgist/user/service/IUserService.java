package com.acgist.user.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acgist.config.APIConstURL;
import com.acgist.user.pojo.dto.AuthoDTO;
import com.acgist.user.pojo.dto.LoginDTO;

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
	@PostMapping(APIConstURL.URL_USER_AUTHO)
	AuthoDTO autho(String name);

	/**
	 * 登陆
	 * @param name 用户名
	 * @param password 用户密码
	 * @return 登陆结果
	 */
	@PostMapping(APIConstURL.URL_USER_LOGIN)
	LoginDTO login(String name, String password);
	
}
