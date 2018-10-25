package com.api.core.gateway.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 网关信息
 */
public interface GatewayReceiverBinding {

	String STREAM_BINDER_RECEIVER = "api_message_gateway_receiver";
	
    @Input(STREAM_BINDER_RECEIVER)
    SubscribableChannel input();
    
}
