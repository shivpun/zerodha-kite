package com.zerodha.kite.algo;

public class ExponentialMovingAverage implements Algo {
	
	private final String field;

	private final int period;
	
	private final double smoothing;
	
	private final SimpleMovingAverage sma;
	
	private double avg = 0;
	
	public ExponentialMovingAverage(String field, int period) {
		this.field = field;
		this.period = period;
		this.smoothing = 2/(period+1);
		this.sma = new SimpleMovingAverage(field, period);
	}

	@Override
	public double cal(double values) {
		this.sma.cal(values);
		if(avg==0 && this.sma.avg()>0) {
			this.avg = this.sma.avg();
		}
		if(avg>0) {
			this.avg = (values - this.avg) * this.smoothing + this.avg;
			return this.avg;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("EMA(" + field + "," + period + ")");
		return sb.toString();
	}
}
