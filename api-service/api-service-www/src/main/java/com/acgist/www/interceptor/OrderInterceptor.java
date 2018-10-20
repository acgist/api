package com.acgist.www.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.acgist.config.APIConstSession;
import com.acgist.pojo.session.SessionUser;
import com.acgist.utils.RedirectUtils;

/**
 * 订单拦截器
 */
@Component
public class OrderInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(APIConstSession.SESSION_USER);
		if(sessionUser == null) {
			RedirectUtils.redirect2get(response, "/user/login");
			return false;
		}
		return true;
	}
	
}
