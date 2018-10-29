package com.api.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.api.core.pojo.vo.ServiceInfo;
import com.api.core.pojo.vo.ServiceInstanceInfo;

/**
 * 服务工具
 */
@Service
public class DiscoveryService {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	public List<ServiceInfo> list() {
		List<ServiceInfo> services = new ArrayList<>();
		discoveryClient
		.getServices()
		.forEach(serviceId -> {
			ServiceInfo service = new ServiceInfo(serviceId);
			List<ServiceInstanceInfo> instances = new ArrayList<>();
			discoveryClient
			.getInstances(serviceId)
			.forEach(instance -> {
				instances.add(new ServiceInstanceInfo(serviceId, instance.getUri().toString()));
			});
			service.setInstances(instances);
			services.add(service);
		});
		return services;
	}
	
}
