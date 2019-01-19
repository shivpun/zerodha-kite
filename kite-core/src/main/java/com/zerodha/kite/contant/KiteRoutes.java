package com.zerodha.kite.contant;

import static com.zerodha.kite.contant.KiteConstant.X_KITE_VERSION;

import java.util.Date;

public class KiteRoutes {
	
	public static final String KITE_WEB_SOCKET_URL = "wss://ws.zerodha.com/?api_key=kitefront&user_id={USER_ID}&public_token={PUBLIC_TOKEN}"+(new Date().getTime())+"&user-agent=kite3-web&version="+X_KITE_VERSION;
	
	public static final String KITE_HOST = "ws.zerodha.com";
}
