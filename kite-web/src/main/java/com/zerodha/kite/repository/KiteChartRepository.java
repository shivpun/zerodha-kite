package com.zerodha.kite.repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.zerodha.kite.contant.KiteChartQuery;
import com.zerodha.kite.domain.Algorithm;
import com.zerodha.kite.domain.KiteChart;
import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.util.DateUtil;

@Repository
public class KiteChartRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void kiteJdbcChart(KiteChart kiteChartMsg) {
		KiteChart kiteChart = kiteChartMsg;
		List<OHLC> ohcls = kiteChart.getOhlcs();
		for (OHLC ohcl : ohcls) {
			BigDecimal ohclId = fetchSequence(KiteChartQuery.SEQ_KITE_OHLC);
			jdbcTemplate.update(KiteChartQuery.INSERT_KITE_OHLC, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setBigDecimal(1, ohclId);
					ps.setDouble(2, kiteChart.tokenId);
					ps.setDouble(3, ohcl.getOpen());
					ps.setDouble(4, ohcl.getHigh());
					ps.setDouble(5, ohcl.getClose());
					ps.setDouble(6, ohcl.getLow());
					ps.setDouble(7, ohcl.getUWick());
					ps.setDouble(8, ohcl.getLWick());
					ps.setDouble(9, ohcl.getBody());
					ps.setDouble(10, ohcl.getProfit());
					ps.setDouble(11, ohcl.getVolume());
					ps.setInt(12, kiteChart.getTimeframe());
					ps.setTimestamp(13, DateUtil.getTimeStamp(ohcl.getChartTimeStamp()));
					ps.setString(14, ohcl.getTimeStamp());
					ps.setString(15, ohcl.getCandleType());
					ps.setString(16, ohcl.pattern());
				}
			});
			kiteJdbcAlgo(ohcl.getAlgorithm());
		}
	}

	public void kiteJdbcAlgo(List<Algorithm> algos) {
		for (Algorithm algo : algos) {
			BigDecimal algoId = fetchSequence(KiteChartQuery.SEQ_KITE_ALGO);
			jdbcTemplate.update(KiteChartQuery.INSERT_KITE_ALGO, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setBigDecimal(1, algoId);
					ps.setTimestamp(2, DateUtil.getTimeStamp(algo.getChartDate()));
					ps.setString(3, algo.getName());
					ps.setDouble(4, algo.getToken());
					ps.setDouble(5, algo.values());
					ps.setInt(6, algo.getTimeFrame());
					ps.setString(7, algo.getTimeStamp());
				}
			});
		}
	}

	public BigDecimal fetchSequence(String sequenceQuery) {
		return jdbcTemplate.queryForObject(sequenceQuery, BigDecimal.class);
	}
}
