package com.zerodha.kite.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.zerodha.kite.contant.KiteDateFormat;

@RunWith(SpringRunner.class)
public class DateUtilTest {
	
	@Test
	public void testNotNullGetTimeStamp() {
		Timestamp timestamp = DateUtil.getTimeStamp(new Date());
		assertNotNull(timestamp);
		assertTrue(timestamp instanceof Timestamp);
	}
	
	@Test
	public void testNullGetTimeStamp() {
		Timestamp timestamp = DateUtil.getTimeStamp(null);
		assertNull(timestamp);
	}
	
	@Test
	public void next7() {
		Date fromDate = DateUtil.cal(DateUtil.getDate("1944-12-31", KiteDateFormat.DATE_MM_CHART_FORMAT), 6);
		System.out.println((DateUtil.getDate(fromDate, KiteDateFormat.DATE_MM_CHART_FORMAT)));
	}
}
