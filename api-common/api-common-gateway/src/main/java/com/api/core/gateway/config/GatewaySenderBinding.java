package com.api.core.gateway.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 网关信息发送
 */
public interface GatewaySenderBinding {

	String STREAM_BINDER_SENDER = "api_message_gateway_sender";
	
    @Output(STREAM_BINDER_SENDER)
    MessageChannel output();

}
