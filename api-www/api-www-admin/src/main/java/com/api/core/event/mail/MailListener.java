package com.api.core.event.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.api.core.service.MailService;

/**
 * 邮件事件监听
 */
@Component
public class MailListener implements ApplicationListener<MailEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailListener.class);
	
	@Autowired
	private MailService mailService;
	
	@Override
	public void onApplicationEvent(MailEvent event) {
		if(event == null) {
			LOGGER.info("邮件事件事件为空");
		} else {
			mailService.post(event.getTo(), event.getSubject(), event.getContent());
		}
	}

}
