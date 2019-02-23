package com.zerodha.kite.algo;

import java.util.ArrayList;
import java.util.List;

import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.util.KiteNumberUtil;

public class PivotClassical {

	private final OHLC ohlc;

	private double pivot;

	private List<Double> support = new ArrayList<Double>();

	private List<Double> resistance = new ArrayList<Double>();

	public PivotClassical(OHLC ohlc) {
		this.ohlc = ohlc;
		this.pivot = (this.ohlc.getHigh() + this.ohlc.getLow() + this.ohlc.getClose() + this.ohlc.getOpen()) / 4;
		addSupport((2 * this.pivot) - this.ohlc.getHigh());
		addSupport(this.pivot - this.ohlc.getHigh() + this.ohlc.getLow());
		addSupport(this.ohlc.getLow() - 2 * (this.ohlc.getHigh() - this.pivot));

		addResistance((2 * this.pivot) - this.ohlc.getLow());
		addResistance(this.pivot + this.ohlc.getHigh() - this.ohlc.getLow());
		addResistance(this.ohlc.getHigh() + 2 * (this.pivot - this.ohlc.getLow()));
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
