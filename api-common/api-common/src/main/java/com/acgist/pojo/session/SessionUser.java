package com.acgist.pojo.session;

import java.io.Serializable;

/**
 * session-用户：用户登陆后的session key
 */
public class SessionUser implements Serializable {

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
