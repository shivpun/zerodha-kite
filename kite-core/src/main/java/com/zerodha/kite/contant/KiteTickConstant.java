package com.zerodha.kite.contant;

import static com.zerodha.kite.contant.KiteDateFormat.DATE_MM_FORMAT;

import java.util.Date;

import com.zerodha.kite.util.DateUtil;

public class KiteTickConstant {
	
	public static final int PING_INTERVAL = 2500;
	
	public static final int PONG_CHECK_INTERVAL = PING_INTERVAL;
	
	public static final String TABLE_KITE_DEPTH = "kite_depth";
	
	public static final String TABLE_KITE_TICK = "kite_tick";
	
	public static final String TABLE_CURRENT_KITE_DEPTH = TABLE_KITE_DEPTH+"_"+DateUtil.getDate(new Date(), DATE_MM_FORMAT);
	
	public static final String TABLE_CURRENT_KITE_TICK = TABLE_KITE_TICK+"_"+DateUtil.getDate(new Date(), DATE_MM_FORMAT);

}
