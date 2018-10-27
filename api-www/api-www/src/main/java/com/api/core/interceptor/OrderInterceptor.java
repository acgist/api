package com.api.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.api.core.pojo.session.UserSession;
import com.api.utils.RedirectUtils;

/**
 * 订单拦截器
 */
@Component
public class OrderInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UserSession user = UserSession.get(request);
		if(user == null) {
			RedirectUtils.redirectToGet(response, "/user/login");
			return false;
		}
		return true;
	}
	
}
