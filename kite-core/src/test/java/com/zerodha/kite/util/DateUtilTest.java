package com.zerodha.kite.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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
}
