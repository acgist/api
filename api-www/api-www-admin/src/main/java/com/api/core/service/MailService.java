package com.api.core.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.api.core.controller.MailController;

/**
 * service - 邮件
 */
@Service
public class MailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);
	
	@Value("${spring.mail.username:}")
	private String mail;

	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void test() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(mail);
//		message.setTo(mail);
//		message.setSubject("邮件主题：测试");
//		message.setText("邮件内容：测试邮件");
//		mailSender.send(message);
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mail);
			helper.setTo(mail);
			helper.setSubject("邮件主题：测试HTML");
			StringBuffer htmlBuilder = new StringBuffer();
			htmlBuilder
				.append("<h1>邮件内容：测试邮件</h1>")
				.append("<p>测试内容</p>");
			helper.setText(htmlBuilder.toString(), true);
			mailSender.send(message);
		} catch (Exception e) {
			LOGGER.error("邮件发送异常", e);
		}
		
//		MimeMessage message = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message, true);
//		helper.setFrom(mail);
//		helper.setTo(mail);
//		helper.setSubject("邮件主题：测试附件");
//		helper.setText("邮件内容：测试邮件");
//		FileSystemResource file = new FileSystemResource(new File("文件路径"));
//		helper.addAttachment("文件名称", file);
//		mailSender.send(message);
	}
	
}
