package com.zerodha.kite.util;

import java.math.BigDecimal;

public class KiteNumberUtil {
	
    public static double round(double value) {
    	BigDecimal decimal = new BigDecimal(value);
    	return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static boolean lessThanOneAndGreaterThanZero(Double value) {
    	return value!=null && value.doubleValue()>0 && value.doubleValue()<1;
    }
}
