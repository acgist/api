package com.api.core.pojo.session;

import javax.servlet.http.HttpServletRequest;

import com.api.core.config.APIConstSession;

/**
 * 后台用户
 */
public class AdminSession extends BaseSession {

	private static final long serialVersionUID = 1L;

	public AdminSession(HttpServletRequest request) {
		super(APIConstSession.SESSION_ADMIN, request.getSession());
	}

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
