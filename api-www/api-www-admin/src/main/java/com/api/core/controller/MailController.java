package com.api.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.core.event.mail.MailEvent;
import com.api.core.pojo.layui.LayuiMessage;
import com.api.utils.EventPublisher;

/**
 * controller - 邮件
 */
@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private ApplicationContext context;
	
	@ResponseBody
	@GetMapping("/test")
	public LayuiMessage test(String to) {
		final String subject = "邮件主题：测试";
		final String content = "邮件内容：测试邮件";
		final MailEvent event = new MailEvent(this, to, subject, content);
		EventPublisher.publish(context, event);
		return LayuiMessage.success();
	}
	
}
