package com.zerodha.kite.service;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zerodha.kite.contant.KiteDateFormat;
import com.zerodha.kite.contant.KiteHeader;
import com.zerodha.kite.domain.ChartData;
import com.zerodha.kite.domain.KiteChart;
import com.zerodha.kite.domain.KiteChartUser;
import com.zerodha.kite.repository.KiteChartRepository;
import com.zerodha.kite.util.DateUtil;
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
		KiteChartUser kiteChartUser = chartUser.kiteChartUser();
		Date today = new Date();
		Integer []add = new Integer[] {null,3,5,10,15,30,60};
		while(today.after(DateUtil.getDate(kiteChartUser.getToDate(), KiteDateFormat.DATE_MM_CHART_FORMAT))) {
			for(Integer frame:add) {
				kiteChartUser.setTimeFrame(frame);
				HttpEntity<String> requestEntity = new HttpEntity<String>(KiteHeader.defaultChart(kiteChartUser));
				URI uri = KiteRouteUtil.chart(kiteChartUser);
				System.out.println("KiteURI:\n"+uri.toString());
				ResponseEntity<ChartData> values = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ChartData.class);
				ChartData chartData = values.getBody();
				List<List<Object>> map = (List<List<Object>>) chartData.getData().get("candles");
				KiteChart kiteChart = new KiteChart();
				kiteChart.setTimeframe(kiteChartUser.getTimeFrame());
				kiteChart.setTokenId(kiteChartUser.getToken());
				KiteChartUtil.kiteCharts(map, kiteChart);
				kiteChartRepository.kiteJdbcChart(kiteChart);
				System.out.println(map);
			}
			Date fromDate = DateUtil.cal(DateUtil.getDate(kiteChartUser.getFromDate(), KiteDateFormat.DATE_MM_CHART_FORMAT), 7);
			kiteChartUser.setFromDate(DateUtil.getDate(fromDate, KiteDateFormat.DATE_MM_CHART_FORMAT));
			
			Date toDate = DateUtil.cal(DateUtil.getDate(kiteChartUser.getToDate(), KiteDateFormat.DATE_MM_CHART_FORMAT), 7);
			kiteChartUser.setToDate(DateUtil.getDate(toDate, KiteDateFormat.DATE_MM_CHART_FORMAT));
		}
	}
}
