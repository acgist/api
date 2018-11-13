package com.api.core.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Flux;
import rx.subjects.PublishSubject;

/**
 * 替换默认TurbineController，防止静态资源加载失败的情况
 */
public class TurbineController extends org.springframework.cloud.netflix.turbine.stream.TurbineController {

	public TurbineController(PublishSubject<Map<String, Object>> hystrixSubject) {
		super(hystrixSubject);
	}

	@GetMapping(value = "/actuator/turbine.stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> stream() {
		return super.stream();
	}

}
