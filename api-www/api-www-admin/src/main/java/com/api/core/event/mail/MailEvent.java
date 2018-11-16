package com.api.core.event.mail;

import org.springframework.context.ApplicationEvent;

/**
 * 邮件事件
 */
public class MailEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String to; // 收件人
	private String subject; // 主题
	private String content; // 内容

	public MailEvent(Object source, String to, String subject, String content) {
		super(source);
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

}
