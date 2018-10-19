package com.acgist.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.acgist.api.APICode;
import com.acgist.exception.ErrorCodeException;
import com.acgist.utils.RedirectUtils;

/**
 * 异常处理
 */
@ControllerAdvice
public class APIControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public void exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("系统异常", e);
		if (e instanceof ErrorCodeException) {
			ErrorCodeException exception = (ErrorCodeException) e;
			APICode code = APICode.valueOfCode(exception.getErrorCode());
			RedirectUtils.error(code, exception.getMessage(), request, response);
		} else {
			APICode code = APICode.CODE_9999;
			if(e instanceof HttpRequestMethodNotSupportedException) {
				code = APICode.CODE_4405;
			}
			RedirectUtils.error(code, request, response);
		}
	}

}
