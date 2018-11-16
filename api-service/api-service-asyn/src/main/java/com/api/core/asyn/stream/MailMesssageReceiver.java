package com.api.core.asyn.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import com.api.core.asyn.config.MailReceiverBinding;
import com.api.core.asyn.pojo.message.MailMessage;
import com.api.core.asyn.service.MailService;

/**
 * 邮件信息队列接收器
 */
@EnableBinding(MailReceiverBinding.class)
public class MailMesssageReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailMesssageReceiver.class);
	
	@Autowired
	private MailService mailService;
	
	@StreamListener(MailReceiverBinding.MAIL_RECEIVER_STREAM_BINDER)
	public void receive(Message<MailMessage> message) {
		MailMessage mailMessage = message.getPayload();
		if(mailMessage == null) {
			LOGGER.warn("发送邮件信息为空");
		} else {
			mailService.post(mailMessage.getTo(), mailMessage.getSubject(), mailMessage.getContent());
		}
	}

}
