package com.acgist.api.executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.api.APICode;
import com.acgist.api.request.APIRequest;
import com.acgist.api.response.APIResponse;
import com.acgist.exception.ErrorCodeException;

/**
 * 请求执行者
 */
@Component
@Scope("prototype")
public abstract class APIExecutor<T extends APIRequest, K extends APIResponse> {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIExecutor.class);

	protected T request;
	protected K response;

	/**
	 * 执行器
	 */
	protected abstract void execute();

	/**
	 * 响应
	 */
	public Map<String, String> response(T request) {
		init(request);
		execute();
		return this.response.response();
	}

	/**
	 * 初始化
	 */
	private void init(T request) {
		this.request = request;
		this.response = buildResponse();
	}

	/**
	 * 默认生成响应
	 */
	private K buildResponse() {
		K response = buildAPIResponse();
		response.valueOfRequest(this.request); // 设置需要原样返回的参数
		return response;
	}

	/**
	 * 生成新的响应
	 * TODO 优化泛型
	 */
	@SuppressWarnings("unchecked")
	private K buildAPIResponse() {
		final ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		final Type[] types = parameterizedType.getActualTypeArguments();
		final Class<K> clazz = (Class<K>) types[1]; // 获取第二个泛型
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			LOGGER.error("生成响应异常", e);
		}
		throw new ErrorCodeException(APICode.CODE_9999);
	}

}
