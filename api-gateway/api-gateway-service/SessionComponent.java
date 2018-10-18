package com.acgist.common.api;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.acgist.common.api.request.APIRequest;
import com.acgist.common.api.response.APIResponse;
import com.acgist.common.code.APIType;

/**
 * 请求数据
 * 	session：发生异常、重定向、请求转发均不会丢失数据
 * 	request：发生异常、请求转发均不会丢失数据，重定向时会丢失数据
 */
@Component
@Scope("request")
//@Scope("session")
public class SessionComponent implements Serializable {

	private static final long serialVersionUID = 1L;

	private long time; // 请求时间
	private boolean process = false; // 处理中
	private String queryId; // 唯一标识

	private String json; // 原始请求数据
	private APIType apiType; // 请求类型
	private APIRequest request; // 请求数据
	private APIResponse response; // 响应数据

	private static final long MAX_PROCESS_TIME = 2 * 60 * 1000L; // 交易请求处理超时时间：两分钟

	public static final SessionComponent getInstance(ApplicationContext context) {
		return context.getBean(SessionComponent.class);
	}

	public String getQueryId() {
		return queryId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public APIType getApiType() {
		return apiType;
	}

	public void setApiType(APIType apiType) {
		this.apiType = apiType;
	}

	public APIRequest getRequest() {
		return request;
	}

	public void setRequest(APIRequest request) {
		this.request = request;
	}

	public APIResponse getResponse() {
		return response;
	}

	public void setResponse(APIResponse response) {
		this.response = response;
	}

	/**
	 * 是否处于处理过程中，超过指定时间默认处理完成
	 */
	private boolean inProcess() {
		if (process) {
			return (process = (System.currentTimeMillis() - this.time < MAX_PROCESS_TIME));
		}
		return false;
	}

	/**
	 * 创建新请求
	 */
	private void newProcess(String queryId) {
		this.process = true;
		this.queryId = queryId;
		this.time = System.currentTimeMillis();
	}

	/**
	 * 创建新请求：true-创建成功，false-已有请求在处理
	 */
	public boolean buildProcess(String queryId) {
		synchronized (this) {
			if (inProcess()) {
				return false;
			} else {
				newProcess(queryId);
				return true;
			}
		}
	}

	/**
	 * 完成请求
	 */
	public void completeProcess(HttpServletRequest request) {
		synchronized (this) {
			this.process = false;
		}
	}

}