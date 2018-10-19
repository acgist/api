package com.acgist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.acgist.config.APIConstSession;

/**
 * 订单拦截器
 */
@Component
public class OrderInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.getSession().getAttribute(APIConstSession.SESSION_USER);
		return true;
	}
	
}
