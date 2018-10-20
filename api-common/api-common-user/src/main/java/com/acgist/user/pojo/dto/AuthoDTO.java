package com.acgist.user.pojo.dto;

import com.acgist.pojo.dto.BaseDTO;

/**
 * 结果
 */
public class AuthoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

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
