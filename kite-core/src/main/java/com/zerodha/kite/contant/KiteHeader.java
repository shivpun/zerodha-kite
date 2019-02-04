package com.zerodha.kite.contant;

import static com.zerodha.kite.contant.KiteRoutes.KITE_CHART_HOST;
import static com.zerodha.kite.contant.KiteRoutes.KITE_HOST;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.zerodha.kite.domain.KiteChartUser;
import com.zerodha.kite.domain.KiteMessage;

public class KiteHeader {

	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";

	public static final String GZIP_ACCEPT_ENCODING = "gzip, deflate, br";

	public static final String NO_CACHE = "no-cache";

	public static void defaultWebSocket(KiteMessage kiteMessage) {
		if (kiteMessage != null) {
			kiteMessage.addHeader("User-Agent", USER_AGENT);
			kiteMessage.addHeader("Host", KITE_HOST);
			kiteMessage.addHeader("Origin", KITE_HOST);
			kiteMessage.addHeader("Accept-Encoding", GZIP_ACCEPT_ENCODING);
			kiteMessage.addHeader("Cache-Control", NO_CACHE);
			kiteMessage.addHeader("Pragma", NO_CACHE);
		}
	}

	public static HttpHeaders defaultChart(KiteChartUser chartUser) {
		HttpHeaders headers = new HttpHeaders();
		if (chartUser != null) {
			headers.add("User-Agent", USER_AGENT);
			headers.add("Origin", KITE_CHART_HOST);
			headers.add("Cache-Control", NO_CACHE);
			headers.add("Pragma", NO_CACHE);
			headers.add("Cookie", chartUser.getCookie());
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		}
		return headers;
	}
}
