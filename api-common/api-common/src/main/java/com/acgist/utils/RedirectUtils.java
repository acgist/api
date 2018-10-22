package com.acgist.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import com.acgist.api.APICode;

/**
 * 重定向工具
 */
public class RedirectUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedirectUtils.class);

	private static final String ERROR_LOCATION = "/error?code=%s&message=%s"; // 错误页面链接

	/**
	 * 跳转错误提示页面
	 */
	public static final void error(APICode code, HttpServletRequest request, HttpServletResponse response) {
		error(code, code.getMessage(), request, response);
	}

	/**
	 * 跳转错误提示页面
	 */
	public static final void error(APICode code, String message, HttpServletRequest request, HttpServletResponse response) {
		message = APICode.message(code, message);
		requestDispatcher(request, response, String.format(ERROR_LOCATION, code.getCode(), URLUtils.encoding(message)));
	}

	/**
	 * 请求转发
	 * 
	 * @param request  请求
	 * @param response 响应
	 * @param location 地址
	 */
	public static final void requestDispatcher(HttpServletRequest request, HttpServletResponse response, String location) {
		try {
			request.getRequestDispatcher(location).forward(request, response);
		} catch (ServletException e) {
			LOGGER.error("请求转发异常：{}", location, e);
		} catch (IOException e) {
			LOGGER.error("请求转发异常：{}", location, e);
		}
	}

	/**
	 * 303重定向
	 * 
	 * @param response 响应
	 * @param location 地址
	 */
	public static final ModelAndView redirect2get(HttpServletResponse response, String location) {
		if (response != null) {
			response.setStatus(HttpStatus.SEE_OTHER.value());
			response.setHeader("Location", location);
			response.setHeader("Connection", "close");
		}
		return null;
	}

	/**
	 * 307重定向
	 * 
	 * @param response 响应
	 * @param location 地址
	 */
	public static final void redirect2post(HttpServletResponse response, String location) {
		if (response != null) {
			response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
			response.setHeader("Location", location);
			response.setHeader("Connection", "close");
		}
	}

}
