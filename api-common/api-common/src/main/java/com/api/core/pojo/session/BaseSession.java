package com.api.core.pojo.session;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

/**
 * session管理
 */
public class BaseSession implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected String sessionKey;
	protected transient HttpSession session;
	
	public BaseSession(String sessionKey, HttpSession session) {
		this.sessionKey = sessionKey;
		this.session = session;
	}

	/**
	 * 设置session
	 */
	public void putSession() {
		session.setAttribute(sessionKey, this);
	}
	
}
