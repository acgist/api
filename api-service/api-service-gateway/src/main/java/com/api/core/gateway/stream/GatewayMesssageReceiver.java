package com.api.core.gateway.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import com.api.core.gateway.config.GatewayReceiverBinding;
import com.api.core.gateway.pojo.message.GatewayMessage;
import com.api.data.gateway.repository.GatewayRepository;

@EnableBinding(GatewayReceiverBinding.class)
public class GatewayMesssageReceiver {

	@Autowired
	private GatewayRepository gatewayRepository;
	
	@StreamListener(GatewayReceiverBinding.STREAM_BINDER_RECEIVER)
	public void receive(Message<GatewayMessage> message) {
		GatewayMessage gatewayMessage = message.getPayload();
		if(gatewayMessage.getEntity() != null) {
			gatewayRepository.save(gatewayMessage.getEntity());
		}
	}
	
}
