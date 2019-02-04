package com.zerodha.kite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zerodha.kite.domain.KiteChartUser;

@Configuration
public class KiteChartUserConfiguration {

	@Bean
	public KiteChartUser kiteChartUser(@Value(value = "${kite.chart.userId}") String userId,
			@Value(value = "${kite.chart.publicToken}") String publicToken,
			@Value(value = "${kite.chart.accessToken}") String accessToken,
			@Value(value = "${kite.chart.cookie}") String cookie, @Value(value = "${kite.chart.token}") double token,
			@Value(value = "${kite.chart.toDate}") String toDate,
			@Value(value = "${kite.chart.fromDate}") String fromDate,
			@Value(value = "${kite.chart.timeFrame}") Integer timeFrame) {
		KiteChartUser chartUser = new KiteChartUser();
		chartUser.setUserId(userId);
		chartUser.setPublicToken(publicToken);
		chartUser.setAccessToken(accessToken);
		chartUser.setCookie(cookie);
		chartUser.setToken(token);
		chartUser.setToDate(toDate);
		chartUser.setFromDate(fromDate);
		chartUser.setTimeFrame(timeFrame);
		return chartUser;
	}
}
