package com.zerodha.kite.algo;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleMovingAverage implements Algo {

	private final String field;

	private final int period;

	private double sum;
	
	private double avg = 0.0;

	private double startValue;

	private final Queue<Double> window = new LinkedList<Double>();

	public SimpleMovingAverage(String field, int period) {
		this.field = field;
		this.period = period;
	}

	public void addData(double values) {
		this.startValue = values;
		this.sum += values;
		window.add(values);
		if (window.size() >= period) {
			this.avg = sum/period;
			this.startValue = window.remove();
			this.sum -= this.startValue;
		}
	}

	public double avg() {
		return avg;
	}

	public double cal(double avg, double startValue, double value) {
		this.avg = avg;
		return this.avg + (value - startValue) / period;
	}

	public double getStartValue() {
		return startValue;
	}

	public double cal(double values) {
		addData(values);
		return avg();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SMA(" + field + "," + period + ")");
		return sb.toString();
	}
}
