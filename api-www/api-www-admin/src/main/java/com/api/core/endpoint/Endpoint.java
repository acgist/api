package com.api.core.endpoint;

import java.util.stream.Stream;

public enum Endpoint {
	
	actuator("/actuator", "端点列表", EndpointExecutor.METHOD_GET),
	actuator_bus_refresh("/actuator/bus-refresh", "刷新配置", EndpointExecutor.METHOD_POST),
	actuator_bus_refresh_destination("/actuator/bus-refresh/{destination}", "刷新配置", EndpointExecutor.METHOD_POST),
	actuator_channels("/actuator/channels", "消息通道", EndpointExecutor.METHOD_GET),
	actuator_shutdown("/actuator/shutdown", "关闭应用", EndpointExecutor.METHOD_POST);
	
	private String path;
	private String name;
	private String method;

	private Endpoint(String path, String name, String method) {
		this.path = path;
		this.name = name;
		this.method = method;
	}

	public static final Endpoint valueOfHref(String href) {
		int index = href.indexOf(EndpointExecutor.HEAD_ACTUATOR);
		String path = href.substring(index);
		return Stream.of(Endpoint.values())
		.filter(value -> value.path.equals(path))
		.findFirst()
		.orElse(null);
	}
	
	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public String getMethod() {
		return method;
	}

}
