package com.zerodha.kite.contant;

import java.util.ArrayList;
import java.util.List;

public class KiteFibonacci {
	
	public static final String TREND_UP = "UP";
	
	public static final String TREND_DOWN = "DOWN";
	
	public static final List<Double> FIBONACCI_PERCENT = new ArrayList<Double>(); 
	
	static {
		FIBONACCI_PERCENT.add(0.236);
		FIBONACCI_PERCENT.add(0.382);
		FIBONACCI_PERCENT.add(0.50);
		FIBONACCI_PERCENT.add(0.618);
		FIBONACCI_PERCENT.add(0.786);
		FIBONACCI_PERCENT.add(1.0);
		FIBONACCI_PERCENT.add(1.382);
		FIBONACCI_PERCENT.add(1.618);
		FIBONACCI_PERCENT.add(2.618);
		FIBONACCI_PERCENT.add(4.236);
	}
}
