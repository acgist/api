package com.api.core.pojo.vo;

import java.util.List;

/**
 * 服务信息
 */
public class ServiceInfo {

	private String name;
	List<ServiceInstanceInfo> instances;

	public ServiceInfo() {
	}

	public ServiceInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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