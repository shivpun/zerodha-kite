package com.zerodha.kite.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import com.zerodha.kite.algo.Algo;
import com.zerodha.kite.algo.BollingerBandPercent;
import com.zerodha.kite.algo.SimpleMovingAverage;
import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.util.KiteExprUtil;
import com.zerodha.kite.util.KiteNumberUtil;

@Component(value="ohlcProcessor")
public class OHLCProcessor implements ItemProcessor<List<OHLC>, List<Map<Integer, Object[]>>> {
	
	@Value(value = "#{'open,close,high,low'.split(',')}")
	private List<String> cmp;

	@Override
	public List<Map<Integer, Object[]>> process(List<OHLC> item) throws Exception {
		Algo sma50 = new SimpleMovingAverage("close", 50);
		Algo sma200 = new SimpleMovingAverage("close", 200);
		
		List<Map<Integer, Object[]>> maps = new ArrayList<Map<Integer, Object[]>>();
		Map<Integer, Object[]> map = new HashMap<>();
		int count = 1;

		BollingerBandPercent bandPercent = new BollingerBandPercent("close", 20);
		OHLC p_oh = null;
		for (OHLC ohlc : item) {
			double up = 0, down = 0;
			List<Object> obj = new ArrayList<Object>();
			System.out.println("ohcl charttime:" + ohlc.getTimeStamp() + " | Timeframe:" + ohlc.getTimeframe());
			obj.add(ohlc.getTimeframe());
			obj.add(ohlc.getOpen());
			obj.add(ohlc.getHigh());
			obj.add(ohlc.getClose());
			obj.add(ohlc.getLow());
			obj.add(ohlc.getVolume());
			obj.add(ohlc.getCandleType());
			obj.add(KiteNumberUtil.round(sma50.cal(ohlc.getClose())));
			obj.add(KiteNumberUtil.round(sma200.cal(ohlc.getClose())));
			bandPercent.addData(ohlc.getClose());
			obj.add(bandPercent.cal());
			if (p_oh != null) {
				EvaluationContext context = KiteExprUtil.context(ohlc, p_oh);
				for(String v:cmp) {
					String expression = "#p."+v+">"+"#c."+v;
					if(KiteExprUtil.cmp(context, expression, Boolean.class)) {
						down = down + 1;
					} else {
						up = up + 1;
					}
				}
				obj.add(up);
				obj.add(down);
				String expression = "#p.volume>#c.volume";
				if(KiteExprUtil.cmp(context, expression, Boolean.class)) {
					obj.add("INC");
				} else {
					obj.add("DEC");
				}
			} else {
				obj.add(up);
				obj.add(down);
				obj.add("START");
			}
			obj.add(ohlc.getTimeStamp());
			map.put(count, obj.toArray(new Object[obj.size()]));
			count = count + 1;
			p_oh = ohlc;
		}

		maps.add(map);
		return maps;
	}
}
