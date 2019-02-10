package com.zerodha.kite.util;

import static com.zerodha.kite.contant.KiteConstant.ZERO;

import com.zerodha.kite.contant.KiteChartConstant;
import com.zerodha.kite.domain.OHLC;

public class CandleStickUtil {
	
	public static void candleStick(OHLC ohlc) {
		marubozu(ohlc);
		candleDoji(ohlc);
	}
	
	public static boolean marubozu(OHLC ohlc) {
		boolean flag = ohlc.getLWick()==0 && ohlc.getUWick()==0 && Math.abs(ohlc.getBody())>0;
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_MARUBOZU);
		}
		return flag;
	}
	
	public static void candleDoji(OHLC ohlc) {
		gravestoneDoji(ohlc);
		dragonflyDoji(ohlc);
		longleggedDoji(ohlc);
		doji(ohlc);
		dojiStar(ohlc);
		fourPriceDoji(ohlc);
		dojiLWick(ohlc);
		dojiUWick(ohlc);
	}
	
	public static boolean gravestoneDoji(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && ohlc.getUWick()>0 && ZERO.equals(ohlc.getLWick());
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_GRAVESTONE_DOJI);
		}
		return flag;
	}
	
	public static boolean dragonflyDoji(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && ohlc.getLWick()>0 && ZERO.equals(ohlc.getUWick());
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_DRAGONFLY_DOJI);
		}
		return flag;
	}
	
	public static boolean longleggedDoji(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && ohlc.getUWick()>0 && ohlc.getLWick()>0;
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_LONG_LEGGED_DOJI);
		}
		return flag;
	}
	
	public static boolean dojiStar(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && ohlc.getUWick()==ohlc.getLWick();
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_DOJI_STAR);
		}
		return flag;
	}
	
	public static boolean fourPriceDoji(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && KiteNumberUtil.lessThanOneAndGreaterThanZero(ohlc.getLWick()) && KiteNumberUtil.lessThanOneAndGreaterThanZero(ohlc.getUWick());
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_4_PRICE_DOJI);
		}
		return flag;
	}
	
	public static boolean doji(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && (ohlc.getLWick()>0 && ohlc.getUWick()>0);
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_DOJI);
		}
		return flag;
	}
	
	public static boolean dojiUWick(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && ohlc.getUWick()<ohlc.getLWick();
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_DRAGON_DOJI);
		}
		return flag;
	}
	
	public static boolean dojiLWick(OHLC ohlc) {
		boolean flag = ZERO.equals(ohlc.getBody()) && ohlc.getLWick()<ohlc.getUWick();
		if(flag) {
			ohlc.addPattern(KiteChartConstant.CANDLE_GRAVE_DOJI);
		}
		return flag;
	}
}
