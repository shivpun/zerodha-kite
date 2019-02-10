package com.zerodha.kite.util;

import java.util.ArrayList;
import java.util.List;

import com.zerodha.kite.algo.Algo;
import com.zerodha.kite.algo.SimpleMovingAverage;
import com.zerodha.kite.domain.Algorithm;
import com.zerodha.kite.domain.KiteChart;
import com.zerodha.kite.domain.OHLC;

public class KiteChartUtil {

	public static List<OHLC> kiteCharts(List<List<Object>> values, KiteChart kitechart) {
		List<OHLC> ohlcs = new ArrayList<OHLC>();
		Algorithm sma200 = sma(kitechart, 200);
		Algorithm sma50 = sma(kitechart, 50);
		for (List<Object> vals : values) {
			OHLC ohlc = new OHLC();
			ohlc.setTimeStamp(String.valueOf(vals.get(0)));
			ohlc.setChartTimeStamp(ohlc.getTimeStamp());
			ohlc.setOpen(Double.valueOf(String.valueOf(vals.get(1))));
			ohlc.setHigh(Double.valueOf(String.valueOf(vals.get(2))));
			ohlc.setLow(Double.valueOf(String.valueOf(vals.get(3))));
			ohlc.setClose(Double.valueOf(String.valueOf(vals.get(4))));
			ohlc.setVolume(Double.valueOf(String.valueOf(vals.get(5))));
			CandleStickUtil.candleStick(ohlc);
			
			sma(sma200, ohlc);
			sma(sma50, ohlc);
			
			ohlcs.add(ohlc);
			kitechart.addOHCL(ohlc);
		}
		return ohlcs;
	}
	
	public static void sma(Algorithm algorithm, OHLC ohlc) {
		Algorithm algo = algorithm.create();
		algo.setChartDate(ohlc.getChartTimeStamp());
		algo.setTimeStamp(ohlc.getTimeStamp());
		algo.cal(ohlc.getClose());
		ohlc.addAlgo(algo);
	}
	
	public static Algorithm sma(KiteChart kitechart, int period) {
		Algorithm algorithm = new Algorithm();
		algorithm.setTimeFrame(kitechart.getTimeframe());
		algorithm.setToken(kitechart.getTokenId());
		Algo sma = new SimpleMovingAverage("close", period);
		algorithm.setAlgo(sma);
		algorithm.setName(sma.toString());
		return algorithm;
	}
}
