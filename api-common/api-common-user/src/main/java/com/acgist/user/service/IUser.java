package com.acgist.user.service;

import com.acgist.user.pojo.dto.AuthoDTO;
import com.acgist.user.pojo.dto.LoginDTO;

/**
 * 用户
 */
public interface IUser {
	
	/**
	 * 获取用户接口鉴权信息
	 * @param name 用户名
	 * @return 鉴权信息
	 */
	AuthoDTO autho(String name);

	/**
	 * 登陆
	 * @param name 用户名
	 * @param password 用户密码
	 * @return 登陆结果
	 */
	LoginDTO login(String name, String password);
	
}
