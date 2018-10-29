package com.api.core.pojo.vo;

public class ServiceInstanceInfo {

	private String name;
	private String uri;

	public ServiceInstanceInfo() {
	}

	public ServiceInstanceInfo(String name, String uri) {
		this.name = name;
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
