package com.api.core.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import com.api.core.gateway.config.GatewayBindingConfig;

@EnableBinding(GatewayBindingConfig.class)
public class GatewayMesssageReceiver {

	@StreamListener(GatewayBindingConfig.STREAM_BINDER_RECEIVER)
	public void receive(Message<String> message) {
		System.out.println("message=" + message.getPayload());
	}
	
}
