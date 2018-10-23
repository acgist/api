package com.api.core.user.pojo.dto;

import com.api.core.pojo.dto.ResultDTO;

/**
 * 登陆
 */
public class LoginDTO extends ResultDTO {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
