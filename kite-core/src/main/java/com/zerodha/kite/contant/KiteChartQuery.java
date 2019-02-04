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
			"timeframe ," + 
			"time ," +
			"chartTime, "+
			"candleType," +
			"pattern"+
			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
}
