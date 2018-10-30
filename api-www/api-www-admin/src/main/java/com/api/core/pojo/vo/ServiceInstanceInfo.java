package com.api.core.pojo.vo;

import java.util.List;

/**
 * 服务实例
 */
public class ServiceInstanceInfo {

	private String serviceId;
	private String uri;
	private List<EndpointInfo> endpointInfos;

	public ServiceInstanceInfo() {
	}

	public ServiceInstanceInfo(String serviceId, String uri) {
		this.serviceId = serviceId;
		this.uri = uri;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<EndpointInfo> getEndpointInfos() {
		return endpointInfos;
	}

	public void setEndpointInfos(List<EndpointInfo> endpointInfos) {
		this.endpointInfos = endpointInfos;
	}

}
