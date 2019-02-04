package com.zerodha.kite.algo;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleMovingAverage implements Algo {
	
	private final String field;
	
	private final int period;
	
	private double sum;
	
	private final Queue<Double> window = new LinkedList<Double>();
	
	public SimpleMovingAverage(String field, int period) {
		this.field = field;
		this.period = period;
	}
	
	public void addData(double values) {
		this.sum += values;
		window.add(values);
		if(window.size()>period) {
			this.sum -= window.remove();
		}
	}
	
	public double avg() {
		if(window.isEmpty()) {
			return 0.0;
		}
		return sum/period;
	}
	
	public double cal(double values) {
		addData(values);
		return avg();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SMA("+field+","+period+")");
		return sb.toString();
	}
}
