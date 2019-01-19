package com.zerodha.kite.util;

import static com.zerodha.kite.contant.KiteRoutes.KITE_WEB_SOCKET_URL;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class KiteRouteUtil {

	public static URI websocket(String userId, String publicToken) {
		return UriComponentsBuilder.fromUriString(KITE_WEB_SOCKET_URL)
									.buildAndExpand(new Object[] { userId, publicToken })
									.encode()
									.toUri();
	}
}
