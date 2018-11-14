package com.api.core.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.api.core.gateway.config.GatewaySenderBinding;
import com.api.core.gateway.pojo.message.GatewayMessage;
import com.api.data.gateway.pojo.entity.GatewayEntity;

/**
 * 网关信息放入消息队列
 */
@EnableBinding(GatewaySenderBinding.class)
public class GatewayMesssageSender {

    @Autowired
    @Output(GatewaySenderBinding.STREAM_BINDER_SENDER)
    private MessageChannel channel;
	
	public void send(GatewayEntity entity) {
		GatewayMessage message = new GatewayMessage();
		message.setEntity(entity);
		channel.send(MessageBuilder.withPayload(message).build());
	}
	
}
