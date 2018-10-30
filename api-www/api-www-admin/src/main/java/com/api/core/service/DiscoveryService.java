package com.api.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.api.core.endpoint.impl.ActuatorExecutor;
import com.api.core.pojo.vo.ServiceInfo;
import com.api.core.pojo.vo.ServiceInstanceInfo;

/**
 * 服务工具
 */
@Service
public class DiscoveryService {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	/**
	 * 查询所有服务
	 */
	public List<ServiceInfo> services() {
		List<ServiceInfo> services = new ArrayList<>();
		discoveryClient
		.getServices()
		.forEach(serviceId -> {
			ServiceInfo service = new ServiceInfo(serviceId);
			List<ServiceInstanceInfo> instances = instances(serviceId);
			service.setInstances(instances);
			services.add(service);
		});
		return services;
	}
	
	/**
	 * 查询服务所有实例
	 */
	public List<ServiceInstanceInfo> instances(String serviceId) {
		List<ServiceInstanceInfo> instances = new ArrayList<>();
		discoveryClient
		.getInstances(serviceId)
		.forEach(instance -> {
			ServiceInstanceInfo instanceInfo = new ServiceInstanceInfo(serviceId, instance.getUri().toString());
			ActuatorExecutor executor = new ActuatorExecutor(instance.getUri().toString());
			instanceInfo.setEndpointInfos(executor.endpoints());
			instances.add(instanceInfo);
		});
		return instances;
	}
	
}
