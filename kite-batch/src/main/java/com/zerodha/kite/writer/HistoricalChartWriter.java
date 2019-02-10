package com.zerodha.kite.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.zerodha.kite.domain.OHLC;

public class HistoricalChartWriter implements ItemWriter<List<OHLC>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HistoricalChartWriter.class);

	@Override
	public void write(List<? extends List<OHLC>> items) throws Exception {
		LOGGER.info("Writing historical data from [zerodha] to local storage");
	}
}
