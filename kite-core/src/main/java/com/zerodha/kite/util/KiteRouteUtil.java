package com.zerodha.kite.util;

import static com.zerodha.kite.contant.KiteRoutes.KITE_CHARTS;
import static com.zerodha.kite.contant.KiteRoutes.KITE_WEB_SOCKET_URL;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.util.UriComponentsBuilder;

import com.zerodha.kite.domain.KiteChartUser;

public class KiteRouteUtil {

	public static URI websocket(String userId, String publicToken) {
		return UriComponentsBuilder.fromUriString(KITE_WEB_SOCKET_URL)
									.buildAndExpand(new Object[] { userId, publicToken })
									.encode()
									.toUri();
	}
	
	public static URI chart(KiteChartUser chartUser) {
		List<Object> urlParams = new ArrayList<Object>();
		urlParams.add(chartUser.getToken().intValue());
		urlParams.add(chartUser.getTimeFrame());
		urlParams.add(chartUser.getPublicToken());
		urlParams.add(chartUser.getUserId());
		urlParams.add(chartUser.getAccessToken());
		urlParams.add(chartUser.getFromDate());
		urlParams.add(chartUser.getToDate());
		
		return UriComponentsBuilder.fromUriString(KITE_CHARTS)
									.buildAndExpand(urlParams.toArray(new Object[urlParams.size()]))
									.encode()
									.toUri();
	}
}
