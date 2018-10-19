package com.acgist.service.user.pojo;

import javax.validation.constraints.NotBlank;

import com.acgist.pojo.entity.BaseEntity;

/**
 * 持卡人
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "用户名不能为空")
	private String name;
	private String pubilcKey;
	private String privateKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
