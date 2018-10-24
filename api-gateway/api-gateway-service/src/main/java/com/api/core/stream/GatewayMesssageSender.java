package com.api.core.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.api.core.gateway.config.GatewayBindingConfig;

@EnableBinding(GatewayBindingConfig.class)
public class GatewayMesssageSender {

    @Autowired
    @Output(GatewayBindingConfig.STREAM_BINDER_SENDER)
    private MessageChannel channel;
	
	public void send(String text) {
		channel.send(MessageBuilder.withPayload(text).build());
	}
	
}
