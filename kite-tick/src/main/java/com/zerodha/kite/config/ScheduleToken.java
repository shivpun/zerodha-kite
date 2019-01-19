package com.zerodha.kite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;

import com.neovisionaries.ws.client.WebSocket;
import com.zerodha.kite.domain.KiteMessage;

@Configuration
public class ScheduleToken {
	
	@Autowired
	@Qualifier(value = "websocket.tick")
	private MessageChannel kiteWebSocket;
	
	@Autowired
	private KiteMessage kiteMessage;
	
	@Autowired
	private WebSocket websocket;
	
	@Scheduled(cron = "${kite.websocket.cron}")
	public void triggerKiteMessage() {
		for(String message:kiteMessage.getMessage()) {
			websocket.sendText(message);
		}
	}
}
