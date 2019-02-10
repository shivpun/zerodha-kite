package com.zerodha.kite.domain;

import java.util.ArrayList;
import java.util.List;

public class KiteChart {
	
	public double tokenId;
	
	private Integer timeframe = 1;
	
	private List<OHLC> ohlcs = new ArrayList<OHLC>();
	
	public void addOHCL(OHLC ohlc) {
		ohlcs.add(ohlc);
	}

	public double getTokenId() {
		return tokenId;
	}

	public void setTokenId(double tokenId) {
		this.tokenId = tokenId;
	}

	public Integer getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(Integer timeframe) {
		if(timeframe!=null) {
			this.timeframe = timeframe;
		}
	}

	public List<OHLC> getOhlcs() {
		return ohlcs;
	}

	public void setOhlcs(List<OHLC> ohcls) {
		this.ohlcs = ohcls;
	}
}
