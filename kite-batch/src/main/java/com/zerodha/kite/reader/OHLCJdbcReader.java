package com.zerodha.kite.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.repository.OHLCRespository;

@Component(value = "ohlcJdbcReader")
public class OHLCJdbcReader implements ItemReader<List<OHLC>> {

	@Autowired
	private OHLCRespository ohlcRepository;
	
	private int count = 0;
	
	@Value(value = "#{'1,3,5,10,15,30,60'.split(',')}")
	private List<String> timeframes; 

	@Override
	public List<OHLC> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		List<OHLC> ohlcs = null;
		if(timeframes.size()>count) {
		 ohlcs = ohlcRepository.findByChartTime("2019-02-17", "2019-02-22", timeframes.get(count), 341249);
		count = count + 1;
		}
		System.out.println(ohlcs+" OHLC READER");
		return ohlcs;
	}

}
