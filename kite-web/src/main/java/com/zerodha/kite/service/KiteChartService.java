package com.zerodha.kite.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zerodha.kite.contant.KiteHeader;
import com.zerodha.kite.domain.ChartData;
import com.zerodha.kite.domain.KiteChart;
import com.zerodha.kite.domain.KiteChartUser;
import com.zerodha.kite.repository.KiteChartRepository;
import com.zerodha.kite.util.KiteChartUtil;
import com.zerodha.kite.util.KiteRouteUtil;

@Service
public class KiteChartService {
	
	@Autowired
	private KiteChartUser chartUser;
	
	@Autowired
	private KiteChartRepository kiteChartRepository;
	
	public void chart() {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> requestEntity = new HttpEntity<String>(KiteHeader.defaultChart(chartUser));
		URI uri = KiteRouteUtil.chart(chartUser);
		System.out.println("KiteURI:\n"+uri.toString());
		ResponseEntity<ChartData> values = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ChartData.class);
		ChartData chartData = values.getBody();
		List<List<Object>> map = (List<List<Object>>) chartData.getData().get("candles");
		KiteChart kiteChart = new KiteChart();
		kiteChart.setTimeframe(chartUser.getTimeFrame());
		kiteChart.setTokenId(chartUser.getToken());
		KiteChartUtil.kiteCharts(map, kiteChart);
		kiteChartRepository.kiteJdbcChart(kiteChart);
		System.out.println(map);
	}
}
