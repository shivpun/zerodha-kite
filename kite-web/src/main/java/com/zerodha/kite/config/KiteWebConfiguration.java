package com.zerodha.kite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zerodha.kite.algo.Algo;
import com.zerodha.kite.algo.SimpleMovingAverage;

@Configuration
public class KiteWebConfiguration {

	@Bean(name = { "sma" })
	public Algo sma(@Value(value = "${kite.algo.sma.field}") String field,
			@Value(value = "${kite.algo.sma.period}") Integer period) {
		Algo sma = new SimpleMovingAverage(field, period);
		return sma;
	}
}
