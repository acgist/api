package com.api.core.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/chat.socket")
public class ChatWebSocket {

	private static final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();
	
	@OnMessage
	public void message(String message, Session session) {
		for (Session tmp : SESSIONS.values()) {
			if(tmp.getId().equals(session.getId())) {
				continue;
			}
			tmp.getAsyncRemote().sendText(message);
		}
	}
	
	@OnOpen
	public void open(Session session) {
		SESSIONS.put(session.getId(), session);
	}

	@OnClose
	public void close(Session session) {
		SESSIONS.remove(session.getId());
	}

	@OnError
	public void error(Session session, Throwable e) {
		SESSIONS.remove(session.getId());
	}
	
}
