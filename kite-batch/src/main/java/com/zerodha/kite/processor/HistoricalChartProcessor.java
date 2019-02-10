package com.zerodha.kite.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.zerodha.kite.domain.ChartData;
import com.zerodha.kite.domain.OHLC;

public class HistoricalChartProcessor implements ItemProcessor<ChartData, List<OHLC>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HistoricalChartProcessor.class);

	@Override
	public List<OHLC> process(ChartData item) throws Exception {
		LOGGER.info("Processing historical data from [zerodha] for local storage");
		return new ArrayList<OHLC>();
	}

}
