package com.zerodha.kite.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.zerodha.kite.domain.KiteMessage;
import com.zerodha.kite.domain.KiteUser;
import com.zerodha.kite.util.KiteRouteUtil;
import com.zerodha.kite.websocket.KiteWebSocketHandler;

@Configuration
public class KiteWebSocketConfiguration {

	@Autowired
	@Qualifier(value = "websocket.tick.jdbc")
	private MessageChannel jdbcWebSocketTick;
	
	@Autowired
	private KiteMessage kiteMessage;

	@Bean
	public WebSocket webSocket(KiteWebSocketHandler socketHandler) throws IOException, WebSocketException {
		KiteUser kiteUser = kiteMessage.getKiteUser();
		WebSocket socket = new WebSocketFactory().createSocket(KiteRouteUtil.websocket(kiteUser.getUserId(), kiteUser.getPublicToken()));
		Map<String, String> headers = kiteMessage.getHeader();
		for (Map.Entry<String, String> header : headers.entrySet()) {
			socket.addHeader(header.getKey(), header.getValue());
		}
		socket.addListener(socketHandler);
		socket.connect();
		return socket;
	}
	
	@Bean
	public KiteWebSocketHandler webSocketHandler() {
		KiteWebSocketHandler socketHandler = new KiteWebSocketHandler(jdbcWebSocketTick, kiteMessage);
		return socketHandler;
	}
}
