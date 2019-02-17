package com.zerodha.kite.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zerodha.kite.contant.KiteChartQuery;
import com.zerodha.kite.domain.OHLC;

public interface OHLCRespository extends JpaRepository<OHLC, Serializable> {

	@Query(value = KiteChartQuery.SELECT_KITE_OHLC, nativeQuery = true)
	public List<OHLC> findByChartTime(@Param(value="chartTime_1") String chartTime1, @Param(value="chartTime_2") String chartTime2, @Param(value = "timeframe") String timeframe, @Param(value="tokenId") double tokenId);
}
