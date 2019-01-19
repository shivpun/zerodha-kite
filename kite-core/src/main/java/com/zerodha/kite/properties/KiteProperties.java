package com.zerodha.kite.properties;

import static com.zerodha.kite.contant.KiteConstant.X_KITE_VERSION;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kite")
public class KiteProperties {

	@Value(value = "${api.web:https://kite.zerodha.com/api}")
	private String api;

	@Value(value = "${api.chart:https://kitecharts-aws.zerodha.com/api/chart/{TOKEN}/{TIME_FRAME}minute?public_token={PUBLIC_TOKEN}&user_id={USER_ID}&api_key=kitefront&access_token={ACCESS_TOKEN}&from={FROM_DATE}&to={TO_DATE}&ciqrandom=#{new java.util.Date().getTime()}}")
	private String chartApi;

	@Value(value = "${api.origin:https://kite.zerodha.com}")
	private String origin;

	@Value(value = "${api.websocket.host:ws.zerodha.com}")
	private String websocketHost;

	@Value(value = "${api.websocket:wss://ws.zerodha.com/?api_key=kitefront&user_id={USER_ID}&public_token={PUBLIC_TOKEN}&uid=#{new java.util.Date().getTime()}&user-agent=kite3-web&version="+X_KITE_VERSION+"}")
	private String websocket;
}
