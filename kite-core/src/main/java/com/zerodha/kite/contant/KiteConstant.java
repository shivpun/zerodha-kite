package com.zerodha.kite.contant;

public class KiteConstant {
	
	public static final String X_KITE_VERSION = "1.14.0";
	
	public static final Double ZERO = new Double(0);
	
	/** Product types. */
	public static final String PRODUCT_MIS = "MIS";
	public static final String PRODUCT_CNC = "CNC";
	public static final String PRODUCT_NRML = "NRML";

	/** Order types. */
	public static final String ORDER_TYPE_MARKET = "MARKET";
	public static final String ORDER_TYPE_LIMIT = "LIMIT";
	public static final String ORDER_TYPE_SL = "SL";
	public static final String ORDER_TYPE_SLM = "SL-M";

	/** Variety types. */
	public static final String VARIETY_REGULAR = "regular";
	public static final String VARIETY_BO = "bo";
	public static final String VARIETY_CO = "co";
	public static final String VARIETY_AMO = "amo";

	/** Transaction types. */
	public static final String TRANSACTION_TYPE_BUY = "BUY";
	public static final String TRANSACTION_TYPE_SELL = "SELL";

	/** Position types. */
	public static final String POSITION_DAY = "day";
	public static final String POSITION_OVERNIGHT = "overnight";

	/** Validity types. */
	public static final String VALIDITY_DAY = "DAY";
	public static final String VALIDITY_IOC = "IOC";

	/** Exchanges. */
	public static final String EXCHANGE_NSE = "NSE";
	public static final String EXCHANGE_BSE = "BSE";
	public static final String EXCHANGE_NFO = "NFO";
	public static final String EXCHANGE_BFO = "BFO";
	public static final String EXCHANGE_MCX = "MCX";
	public static final String EXCHANGE_CDS = "CDS";

	/** Margin segments. */
	public static final String MARGIN_EQUITY = "equity";
	public static final String MARGIN_COMMODITY = "commodity";

	/** Instruments segments. */
	public static final String INSTRUMENTS_SEGMENTS_EQUITY = "equity";
	public static final String INSTRUMENTS_SEGMENTS_COMMODITY = "commodity";
	public static final String INSTRUMENTS_SEGMENTS_FUTURES = "futures";
	public static final String INSTRUMENTS_SEGMENTS_CURRENCY = "currency";

	public static final String MODE_FULL = "full"; // Full quote inludes Quote items, market depth, OI, day high OI, day
													// low OI, last traded time, tick timestamp.
	public static final String MODE_QUOTE = "quote"; // Quote includes last traded price, last traded quantity, average traded price,
									// volume, total bid(buy quantity), total ask(sell quantity), open, high, low,
									// close.
	public static final String MODE_LTP = "ltp"; // Only LTP.

	public static final String SUBSCRIBE = "subscribe";
	public static final String UNSUBSCRIBE = "unsubscribe";
	public static final String MODE = "mode";
}
