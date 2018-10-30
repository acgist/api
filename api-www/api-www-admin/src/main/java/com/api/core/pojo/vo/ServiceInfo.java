package com.api.core.pojo.vo;

import java.util.List;

/**
 * 服务信息
 */
public class ServiceInfo {

	private String serviceId;
	List<ServiceInstanceInfo> instances;

	public ServiceInfo() {
	}

	public ServiceInfo(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public List<ServiceInstanceInfo> getInstances() {
		return instances;
	}

	public void setInstances(List<ServiceInstanceInfo> instances) {
		this.instances = instances;
	}

	public int getInstanceCount() {
		return this.instances.size();
	}

}