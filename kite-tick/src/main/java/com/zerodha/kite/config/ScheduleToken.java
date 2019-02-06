package com.zerodha.kite.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;

import com.neovisionaries.ws.client.WebSocket;
import com.zerodha.kite.domain.KiteMessage;
import com.zerodha.kite.util.DateUtil;

@Configuration
public class ScheduleToken {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleToken.class);
	
	@Autowired
	@Qualifier(value = "websocket.tick")
	private MessageChannel kiteWebSocket;
	
	@Autowired
	private KiteMessage kiteMessage;
	
	@Autowired
	private WebSocket websocket;
	
	@Scheduled(cron = "${kite.websocket.cron}")
	public void triggerKiteMessage() {
		Date today = new Date();
		if(DateUtil.minute(today, kiteMessage.getStart())>1) {
			LOGGER.info("[triggerKiteMessage] Re-Trigger start:"+kiteMessage.getStart().getTime()+" | today:"+today);
			for(String message:kiteMessage.getMessage()) {
				websocket.sendText(message);
			}
		}
	}
}
