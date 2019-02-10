package com.zerodha.kite.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Timestamp getTimeStamp(Date date) {
		if (date != null) {
			return new Timestamp(date.getTime());
		}
		return null;
	}

	public static String getDate(Date date, String format) {
		if (date != null) {
			return dateFormat(format).format(date);
		}
		return null;
	}

	public static Date cal(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, num);
		return calendar.getTime();
	}
	
	public static Date getDate(String source, String format) {
		if (source != null) {
			try {
				return dateFormat(format).parse(source);
			} catch (ParseException e) {
				throw new IllegalArgumentException(String.format("Date and format provided are mismatched"));
			}
		}
		return null;
	}
	
	public static java.sql.Date getDate(Date date) {
		if(date!=null) {
			return new java.sql.Date(date.getTime());
		}
		return null;
	}

	public static DateFormat dateFormat(String format) {
		return new SimpleDateFormat(format);
	}
}
