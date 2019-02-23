package com.zerodha.kite.algo;

import java.util.LinkedList;
import java.util.Queue;

import com.zerodha.kite.util.KiteNumberUtil;

public class BollingerBandPercent {

	private final String field;

	private final int period;

	private double upper;

	private double lower;

	private double startValue;

	private double avg = 0.0;

	private double sum = 0.0;
	
	private double percent = 0.0;

	private final Queue<Double> window = new LinkedList<Double>();

	public BollingerBandPercent(String field, int period) {
		this.field = field;
		this.period = period;
	}

	public void addData(double value) {
		this.startValue = value;
		this.sum += value;
		this.window.add(value);
		if (window.size() >= period) {
			this.avg = KiteNumberUtil.round(sum / period);
			double stdDev = stdDev();
			this.upper = this.avg + stdDev * 2;
			this.lower = this.avg - stdDev * 2;
			this.percent = (this.startValue - this.lower)/(this.upper - this.lower);
			this.startValue = this.window.remove();
			this.sum -= this.startValue;
		}
	}
	
	public double cal() {
		return percent;
	}

	public double stdDev() {
		double stdDev = 0.0;
		for (double num : window) {
			stdDev += Math.pow(num - this.avg, 2);
		}
		return Math.sqrt(stdDev / period);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BollingerBandPercent(" + field + "," + period + ")");
		return sb.toString();
	}
}
