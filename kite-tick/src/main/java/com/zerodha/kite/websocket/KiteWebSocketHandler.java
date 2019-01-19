package com.zerodha.kite.websocket;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.CollectionUtils;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.zerodha.kite.domain.KiteMessage;
import com.zerodha.kite.domain.Tick;
import com.zerodha.kite.util.KiteTickUtil;

public class KiteWebSocketHandler extends WebSocketAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KiteWebSocketHandler.class);
	
	private MessageChannel messageChannel;
	
	private KiteMessage kiteMessage;
	
	public KiteWebSocketHandler(MessageChannel messageChannel, KiteMessage kiteMessage) {
		this.messageChannel = messageChannel;
		this.kiteMessage = kiteMessage;
	}
	
	@Override
	public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
		if(kiteMessage!=null && !CollectionUtils.isEmpty(kiteMessage.getMessage())) {
			for(String message:kiteMessage.getMessage()) {
				LOGGER.info(String.format("Kite send message has [%s]", message));
				websocket.sendText(message);
			}
		}
	}

	@Override
	public void onBinaryMessage(WebSocket websocket, byte[] packet) throws Exception {
		try {
			super.onBinaryMessage(websocket, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(messageChannel!=null) {
			List<Tick> ticks = KiteTickUtil.parseBinary(packet);
			int size = ticks.size();
			LOGGER.info(String.format("[%s] Stock Tick size [%s] with data [%s] ", "onBinaryMessage", size, ticks));
			Message<List<Tick>> message = MessageBuilder.withPayload(ticks).build();
			messageChannel.send(message);
		}
	}
}
