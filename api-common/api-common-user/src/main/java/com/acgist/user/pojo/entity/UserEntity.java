package com.acgist.user.pojo.entity;

import javax.validation.constraints.NotBlank;

import com.acgist.pojo.entity.BaseEntity;

/**
 * 用户：接口使用证书鉴权、页面使用密码登陆
 */
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "用户名不能为空")
	private String name;
	@NotBlank(message = "用户密码不能为空")
	private String password;
	private String pubilcKey;
	private String privateKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPubilcKey() {
		return pubilcKey;
	}

	public void setPubilcKey(String pubilcKey) {
		this.pubilcKey = pubilcKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
