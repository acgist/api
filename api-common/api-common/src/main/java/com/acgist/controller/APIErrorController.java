package com.acgist.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.acgist.api.APICode;
import com.acgist.api.response.APIResponse;

/**
 * 错误页面
 */
@Controller
public class APIErrorController implements ErrorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIErrorController.class);
	
	/**
	 * JSON接口错误处理
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/error", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String index(String code, String message, HttpServletResponse response) {
		final APICode apiCode = code(code, response);
		message = message(message, apiCode);
		LOGGER.warn("系统错误（接口），错误代码：{}，错误描述：{}", apiCode.getCode(), message);
		return APIResponse.builder().buildMessage(apiCode, message).response();
	}

	/**
	 * 其他错误处理
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/error")
	public String index(String code, String message, ModelMap model, HttpServletResponse response) {
		final APICode apiCode = code(code, response);
		message = message(message, apiCode);
		model.put("code", apiCode.getCode());
		model.put("message", message);
		LOGGER.warn("系统错误（页面），错误代码：{}，错误描述：{}", apiCode.getCode(), message);
		return getErrorPath();
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	private APICode code(String code, HttpServletResponse response) {
		if(code == null) {
			return APICode.valueOfHTTPCode(response.getStatus());
		}
		return APICode.valueOfCode(code);
	}
	
	private String message(String message, APICode apiCode) {
		if(message == null) {
			message = apiCode.getMessage();
		}
		return message;
	}

}
