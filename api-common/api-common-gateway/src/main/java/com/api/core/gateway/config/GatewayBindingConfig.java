package com.api.core.gateway.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 网关信息
 */
public interface GatewayBindingConfig {

	String STREAM_BINDER_SENDER = "gateway_sender";
	String STREAM_BINDER_RECEIVER = "gateway_receiver";
	
    @Input(STREAM_BINDER_RECEIVER)
    SubscribableChannel input();
    
    @Output(STREAM_BINDER_SENDER)
    MessageChannel output();

}
