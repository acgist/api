package com.acgist.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import com.acgist.api.API;
import com.acgist.service.ServiceParameter;

/**
 * 数据校验工具
 */
public class ValidatorUtils {

	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
	
	/**
	 * 校验数据：
	 * 	成功返回：null
	 * 	失败返回：错误信息
	 */
	public static final String verify(API request) {
		if(request == null) {
			return null;
		}
		final StringBuffer message = new StringBuffer();
		final Set<ConstraintViolation<API>> set = VALIDATOR.validate(request, Default.class);
		if (set != null && !set.isEmpty()) {
			for (ConstraintViolation<API> violation : set) {
				message
					.append(violation.getMessage())
					.append("[")
					.append(violation.getPropertyPath().toString())
					.append("]")
					.append("&");
			}
		}
		if(message.length() == 0) {
			return null;
		}
		return message.substring(0, message.length() - 1);
	}
	
	/**
	 * 校验数据：
	 * 	成功返回：null
	 * 	失败返回：错误信息
	 */
	public static final String verify(ServiceParameter data) {
		if(data == null) {
			return null;
		}
		final StringBuffer message = new StringBuffer();
		final Set<ConstraintViolation<ServiceParameter>> set = VALIDATOR.validate(data, Default.class);
		if (set != null && !set.isEmpty()) {
			for (ConstraintViolation<ServiceParameter> violation : set) {
				message
					.append(violation.getMessage())
					.append("[")
					.append(violation.getPropertyPath().toString())
					.append("]")
					.append("&");
			}
		}
		if(message.length() == 0) {
			return null;
		}
		return message.substring(0, message.length() - 1);
	}
	
}
