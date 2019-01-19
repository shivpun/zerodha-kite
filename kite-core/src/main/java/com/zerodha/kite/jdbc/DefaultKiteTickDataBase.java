package com.zerodha.kite.jdbc;

import static com.zerodha.kite.contant.KiteDateFormat.DATE_MM_FORMAT;
import static com.zerodha.kite.contant.KiteTickConstant.TABLE_CURRENT_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickConstant.TABLE_CURRENT_KITE_TICK;
import static com.zerodha.kite.contant.KiteTickConstant.TABLE_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickConstant.TABLE_KITE_TICK;
import static com.zerodha.kite.contant.KiteTickQuery.CREATE_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickQuery.CREATE_KITE_TICK;
import static com.zerodha.kite.contant.KiteTickQuery.DROP_TRIGGER;
import static com.zerodha.kite.contant.KiteTickQuery.TABLE_EXIST;
import static com.zerodha.kite.contant.KiteTickQuery.TRIGGER_FUNCTION_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickQuery.TRIGGER_FUNCTION_KITE_TICK;
import static com.zerodha.kite.contant.KiteTickQuery.TRIGGER_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickQuery.TRIGGER_KITE_TICK;

import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import com.zerodha.kite.util.DateUtil;

public class DefaultKiteTickDataBase {

	public DefaultKiteTickDataBase(JdbcTemplate jdbcTemplate) {
		defaultKiteTick(jdbcTemplate);
		currentKiteTick(jdbcTemplate);
		defaultKiteDepth(jdbcTemplate);
		currentKiteDepth(jdbcTemplate);
		dropTrigger(jdbcTemplate);
		createTrigger(jdbcTemplate);
	}

	public boolean tableExist(JdbcTemplate jdbcTemplate, String tableName) {
		return jdbcTemplate.queryForObject(TABLE_EXIST, new Object[] { tableName }, Boolean.class);
	}

	public void defaultKiteTick(JdbcTemplate jdbcTemplate) {
		boolean isExist = tableExist(jdbcTemplate, TABLE_KITE_TICK);
		if (!isExist) {
			jdbcTemplate.update(CREATE_KITE_TICK);
		}
	}

	public void defaultKiteDepth(JdbcTemplate jdbcTemplate) {
		boolean isExist = tableExist(jdbcTemplate, TABLE_KITE_DEPTH);
		if (!isExist) {
			jdbcTemplate.update(CREATE_KITE_DEPTH);
		}
	}

	public void currentKiteTick(JdbcTemplate jdbcTemplate) {
		boolean isExist = tableExist(jdbcTemplate, TABLE_CURRENT_KITE_TICK);
		if (!isExist) {
			jdbcTemplate.update(CREATE_KITE_TICK.replace(TABLE_KITE_TICK, TABLE_CURRENT_KITE_TICK));
		}
	}

	public void currentKiteDepth(JdbcTemplate jdbcTemplate) {
		boolean isExist = tableExist(jdbcTemplate, TABLE_CURRENT_KITE_DEPTH);
		if (!isExist) {
			jdbcTemplate.update(CREATE_KITE_DEPTH.replace(TABLE_KITE_DEPTH, TABLE_CURRENT_KITE_DEPTH));
		}
	}

	public void dropTrigger(JdbcTemplate jdbcTemplate) {
		Date backDate = DateUtil.cal(new Date(), -1);
		String kiteCurrentDrop = String.format(DROP_TRIGGER, TABLE_KITE_TICK, TABLE_CURRENT_KITE_TICK);
		String depthCurrentDrop = String.format(DROP_TRIGGER, TABLE_KITE_DEPTH, TABLE_CURRENT_KITE_DEPTH);
		String kiteBackDrop = String.format(DROP_TRIGGER, TABLE_KITE_TICK,
				TABLE_KITE_TICK + "_" + DateUtil.getDate(backDate, DATE_MM_FORMAT));
		String depthBackDrop = String.format(DROP_TRIGGER, TABLE_KITE_DEPTH,
				TABLE_KITE_DEPTH + "_" + DateUtil.getDate(backDate, DATE_MM_FORMAT));
		String[] dropQuery = new String[] { kiteCurrentDrop, depthBackDrop, depthCurrentDrop, kiteBackDrop };
		for (String sql : dropQuery) {
			jdbcTemplate.execute(sql);
		}
	}

	public void createTrigger(JdbcTemplate jdbcTemplate) {
		String[] sqls = new String[] { TRIGGER_FUNCTION_KITE_TICK, TRIGGER_FUNCTION_KITE_DEPTH, TRIGGER_KITE_TICK,
				TRIGGER_KITE_DEPTH };
		for (String sql : sqls) {
			try {
				jdbcTemplate.execute(sql);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
