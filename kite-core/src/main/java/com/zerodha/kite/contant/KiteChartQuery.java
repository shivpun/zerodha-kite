package com.zerodha.kite.contant;

public class KiteChartQuery {
	
	public static final String INSERT_KITE_ALGO = "INSERT INTO ALGO (" + 
			"ALGO_ID,"+
			"CHART_DATE," + 
			"ALGO_NAME," + 
			"TOKEN," + 
			"algo_value,"+
			"TIME_FRAME," + 
			"chartTime"+
			") VALUES (?,?,?,?,?,?,?)";
	
	public static final String SEQ_KITE_OHLC = "SELECT nextval('KITE_OHLC_SEQ')";
	
	public static final String SEQ_KITE_ALGO = "SELECT nextval('KITE_ALGO_SEQ')";
	
	public static final String INSERT_KITE_OHLC = "INSERT INTO OHLC (" + 
			"ohcl_id ," + 
			"token_Id," + 
			"open ," + 
			"high ," + 
			"close," + 
			"low ," + 
			"UW ," + 
			"LW ," + 
			"body ," + 
			"profit," + 
			"volume,"+
			"timeframe ," + 
			"time ," +
			"chartTime, "+
			"candleType," +
			"pattern"+
			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SELECT_KITE_OHLC = "SELECT ohcl_id, token_id, open, high, close, low, uw, lw, body, profit, volume, timeframe, charttime, candletype FROM OHLC WHERE substring(charttime from 1 for 10) between :chartTime_1 and :chartTime_2 and timeframe = :timeframe and token_id = :tokenId order by charttime asc"; 
}
