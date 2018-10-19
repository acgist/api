package com.acgist.controller;

import java.util.Date;
import java.util.Map;

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

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/error", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> index(String code, String message) {
		final APICode apiCode = APICode.valueOfCode(code);
		if(message == null) {
			message = apiCode.getMessage();
		}
		return APIResponse.builder().message(apiCode, message).response();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/error")
	public String index(String code, String message, ModelMap model) {
		final APICode apiCode = APICode.valueOfCode(code);
		if(message == null) {
			message = apiCode.getMessage();
		}
		model.put("code", apiCode.getCode());
		model.put("message", message);
		model.put("date", new Date());
		return getErrorPath();
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
