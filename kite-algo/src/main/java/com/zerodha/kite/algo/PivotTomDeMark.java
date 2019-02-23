package com.zerodha.kite.algo;

import java.util.ArrayList;
import java.util.List;

import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.util.KiteNumberUtil;

public class PivotTomDeMark {

	private final OHLC ohlc;

	private double pivot;

	private List<Double> support = new ArrayList<Double>();

	private List<Double> resistance = new ArrayList<Double>();

	public PivotTomDeMark(OHLC ohlc) {
		this.ohlc = ohlc;
		if (this.ohlc.getClose() < this.ohlc.getOpen()) {
			this.pivot = this.ohlc.getHigh() + 2 * this.ohlc.getLow() + this.ohlc.getClose();
		} else if (this.ohlc.getClose() > this.ohlc.getOpen()) {
			this.pivot = 2 * this.ohlc.getHigh() + this.ohlc.getLow() + this.ohlc.getClose();
		} else if (this.ohlc.getOpen() == this.ohlc.getClose()) {
			this.pivot = this.ohlc.getHigh() + this.ohlc.getLow() + 2 * this.ohlc.getClose();
		}
		addSupport(this.pivot/2 - this.ohlc.getHigh());
		addResistance(this.pivot/2 - this.ohlc.getLow());
	}

	public List<Double> getBuy() {
		return this.support;
	}

	public List<Double> getSell() {
		return this.resistance;
	}

	public double pivot() {
		return KiteNumberUtil.round(this.pivot);
	}

	public void addSupport(double values) {
		System.out.println("Support:" + values);
		this.support.add(KiteNumberUtil.round(values));
	}

	public void addResistance(double values) {
		System.out.println("Resistance:" + values);
		this.resistance.add(KiteNumberUtil.round(values));
	}

	public String name() {
		return "PP_Woodie";
	}
}
