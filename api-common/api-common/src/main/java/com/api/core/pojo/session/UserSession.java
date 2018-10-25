package com.api.core.pojo.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.api.core.config.APIConstSession;

/**
 * session-用户
 */
public class UserSession extends BaseSession {

	private static final long serialVersionUID = 1L;

	public UserSession(HttpServletRequest request) {
		super(APIConstSession.SESSION_USER, request.getSession());
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

	/**
	 * 获取session
	 */
	public static final UserSession get(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserSession user = (UserSession) session.getAttribute(APIConstSession.SESSION_USER);
		return user;
	}
	
}
