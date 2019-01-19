package com.zerodha.kite.contant;

import static com.zerodha.kite.contant.KiteTickConstant.TABLE_CURRENT_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickConstant.TABLE_CURRENT_KITE_TICK;
import static com.zerodha.kite.contant.KiteTickConstant.TABLE_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickConstant.TABLE_KITE_TICK;


public class KiteTickQuery {
	
	public static final String INSERT_KITE_TICK = "INSERT INTO "+TABLE_CURRENT_KITE_TICK+" (" + 
			"tick_id," + 
			"mode," + 
			"tradable," + 
			"instrumentToken," + 
			"lastTradedPrice," + 
			"highPrice," + 
			"lowPrice," + 
			"openPrice," + 
			"closePrice," + 
			"change," + 
			"lastTradeQuantity," + 
			"averageTradePrice," + 
			"volumeTradedToday," + 
			"totalBuyQuantity," + 
			"totalSellQuantity," + 
			"oi," + 
			"openInterestDayHigh," + 
			"openInterestDayLow," + 
			"tickTimestamp," + 
			"lastTradedTime," + 
			"CREATED_TIME " + 
			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp)";
	
	public static final String INSERT_KITE_DEPTH = "INSERT INTO "+TABLE_CURRENT_KITE_DEPTH+" ( "+
			"depth_id," + 
			"tick_id," + 
			"quantity," + 
			"price," + 
			"orders," + 
			"type," + 
			"CREATED_TIME " + 
			") VALUES (?,?,?,?,?,?, current_timestamp)";
	
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
			"candleType" + 
			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SELECT_TOKEN_FROM_INSTRUMENT = "select token_id from kite_instrument_token";
	
	public static final String CREATE_KITE_TICK = "CREATE TABLE kite_tick (" + 
			"TICK_ID NUMERIC NOT NULL PRIMARY KEY," + 
			"mode varchar(15)," + 
			"tradable boolean NOT NULL," + 
			"instrumentToken NUMERIC," + 
			"lastTradedPrice NUMERIC," + 
			"highPrice NUMERIC," + 
			"lowPrice NUMERIC," + 
			"openPrice NUMERIC," + 
			"closePrice NUMERIC," + 
			"change NUMERIC," + 
			"lastTradeQuantity NUMERIC," + 
			"averageTradePrice NUMERIC," + 
			"volumeTradedToday NUMERIC," + 
			"totalBuyQuantity NUMERIC," + 
			"totalSellQuantity NUMERIC," + 
			"oi NUMERIC," + 
			"openInterestDayHigh NUMERIC," + 
			"openInterestDayLow NUMERIC," + 
			"tickTimestamp TIMESTAMP," + 
			"lastTradedTime TIMESTAMP," + 
			"CREATED_TIME TIMESTAMP" + 
			")";
	
	public static final String CREATE_KITE_DEPTH = "CREATE TABLE kite_depth (" + 
			"depth_id NUMERIC NOT NULL PRIMARY KEY," + 
			"TICK_ID NUMERIC," + 
			"quantity NUMERIC," + 
			"price NUMERIC," + 
			"orders NUMERIC," + 
			"type varchar(15)," + 
			"CREATED_TIME TIMESTAMP" + 
			")";
	
	public static final String SEQ_KITE_TICK = "SELECT nextval('KITE_TICK_SEQ')";
	
	public static final String SEQ_KITE_DEPTH = "SELECT nextval('KITE_DEPTH_SEQ')";
	
	public static final String SEQ_KITE_OHLC = "SELECT nextval('KITE_OHLC_SEQ')";
	
	public static final String TABLE_EXIST = "SELECT EXISTS " + 
			"(" + 
			"	SELECT 1" + 
			"	FROM information_schema.tables " + 
			"	WHERE table_schema = 'public'" + 
			"	AND table_name = ?" + 
			")";
	
	public static final String TRIGGER_FUNCTION_KITE_TICK = String.format("CREATE OR REPLACE FUNCTION trigger_kite_tick() RETURNS TRIGGER AS $kite_tick_audit$" + 
			"    BEGIN" + 
			"       IF (TG_OP = 'INSERT') THEN" + 
			"            INSERT INTO %s  SELECT * FROM %s k WHERE k.tick_id=NEW.tick_id;" + 
			"            RETURN NEW;" + 
			"        END IF;" + 
			"        RETURN NULL; " + 
			"    END; " + 
			" $kite_tick_audit$ LANGUAGE plpgsql", TABLE_KITE_TICK, TABLE_CURRENT_KITE_TICK);
	
	public static final String TRIGGER_FUNCTION_KITE_DEPTH = String.format("CREATE OR REPLACE FUNCTION trigger_kite_depth() RETURNS TRIGGER AS $kite_depth_audit$" + 
			"    BEGIN" + 
			"       IF (TG_OP = 'INSERT') THEN" + 
			"            INSERT INTO %s  SELECT * FROM %s k WHERE k.depth_id=NEW.depth_id;" + 
			"            RETURN NEW;" + 
			"        END IF; " + 
			"        RETURN NULL;" + 
			"    END;" + 
			" $kite_depth_audit$ LANGUAGE plpgsql", TABLE_KITE_DEPTH, TABLE_CURRENT_KITE_DEPTH);
	
	public static final String TRIGGER_KITE_TICK = String.format("CREATE TRIGGER kite_tick_audit" + 
			" AFTER INSERT OR UPDATE OR DELETE ON %s" + 
			" FOR EACH ROW EXECUTE PROCEDURE trigger_kite_tick()", TABLE_CURRENT_KITE_TICK);
	
	public static final String TRIGGER_KITE_DEPTH = String.format("CREATE TRIGGER kite_depth_audit" + 
			" AFTER INSERT OR UPDATE OR DELETE ON %s" + 
			" FOR EACH ROW EXECUTE PROCEDURE trigger_depth_tick()", TABLE_CURRENT_KITE_DEPTH);
	
	public static final String DROP_TRIGGER = "DROP TRIGGER IF EXISTS %s_audit ON %s";
	
	
}
