package com.zerodha.kite.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.client.RestTemplate;

import com.zerodha.kite.domain.ChartData;

public class HistoricalChartReader implements ItemReader<ChartData> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HistoricalChartReader.class);
	
	private int count = 0;

	@Override
	public ChartData read()	throws Exception {
		LOGGER.info("Reading historical data from [zerodha] via HTTPS request");
		if(count>=5) {
			return null;
		}
		count = count + 1;
		return new ChartData();
	}
	
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
