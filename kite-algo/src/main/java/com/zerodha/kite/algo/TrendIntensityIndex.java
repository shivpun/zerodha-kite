package com.zerodha.kite.algo;

import com.zerodha.kite.contant.KiteConstant;

public class TrendIntensityIndex implements Algo {
	
	private final String field;

	private final int period;
	
	private final int speriod;
	
	private final int mperiod;
	
	private final SimpleMovingAverage sma;
	private final SimpleMovingAverage psma;
	private final SimpleMovingAverage nsma;
	
	private double dev;
	
	private double pDev;
	
	private double nDev;
	
	private double spDev;
	
	private double snDEv;
	
	private double tti;
	
	public TrendIntensityIndex(String field, int period, int speriod) {
		this.field = field;
		this.period = period;
		this.speriod = speriod;
		this.mperiod = this.period%2==0?this.period/2:(this.period + 1)/2;
		this.sma = new SimpleMovingAverage(field, period);
		this.psma = new SimpleMovingAverage(field, mperiod);
		this.nsma = new SimpleMovingAverage(field, mperiod);
	}

	@Override
	public double cal(double values) {
		this.sma.cal(values);
		if(this.sma.avg()>0) {
			this.dev = values - this.sma.avg();
			this.nDev = Math.abs(this.dev);
			this.pDev = Math.abs(this.dev);
			this.snDEv = nsma.cal(nDev);
			this.spDev = psma.cal(pDev);
			if(this.nsma.avg() > 0 && this.psma.avg()>0) {
				this.tti = (this.spDev/(this.spDev+this.snDEv)*100);
				return tti;
			}
		}
		return 0;
	}
	
	public String signal() {
		if(this.tti >80) {
			return KiteConstant.TRANSACTION_TYPE_SELL;
		}
		if(this.tti<20) {
			return KiteConstant.TRANSACTION_TYPE_BUY;
		}
		return KiteConstant.TRANSACTION_TYPE_WAIT;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TTI(" + field + "," + period + ")");
		return sb.toString();
	}
}
