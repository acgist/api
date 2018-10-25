package com.api.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.core.exception.ErrorCodeException;
import com.api.core.gateway.APICode;
import com.api.utils.RedirectUtils;

/**
 * 异常处理
 */
@ControllerAdvice
public class APIControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIControllerAdvice.class);
	
	/**
	 * 异常处理，异常和状态码对应参考：DefaultHandlerExceptionResolver
	 */
	@Primary
	@ExceptionHandler(Exception.class)
	public void exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("系统异常", e);
		APICode code = APICode.valueOfThrowable(e, response);
		if (e instanceof ErrorCodeException) {
			RedirectUtils.error(code, e.getMessage(), request, response);
		} else {
			RedirectUtils.error(code, request, response);
		}
	}

}
