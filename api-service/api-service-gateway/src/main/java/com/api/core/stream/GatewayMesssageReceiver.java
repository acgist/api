package com.api.core.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import com.api.core.gateway.config.GatewayReceiverBinding;
import com.api.core.gateway.config.GatewaySenderBinding;

@EnableBinding(GatewaySenderBinding.class)
public class GatewayMesssageReceiver {

	@StreamListener(GatewayReceiverBinding.STREAM_BINDER_RECEIVER)
	public void receive(Message<String> message) {
		System.out.println("message=" + message.getPayload());
	}
	
}
