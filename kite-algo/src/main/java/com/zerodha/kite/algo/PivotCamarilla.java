package com.zerodha.kite.algo;

import java.util.ArrayList;
import java.util.List;

import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.util.KiteNumberUtil;

public class PivotCamarilla {
	
	private final OHLC ohlc;
	
	private double pivot;
	
	private List<Double> support = new ArrayList<Double>();
	
	private List<Double> resistance = new ArrayList<Double>();
	
	public PivotCamarilla(OHLC ohlc) {
		this.ohlc = ohlc;
		this.pivot = ohlc.getProfit();
		addSupport(this.ohlc.getClose() - this.ohlc.getProfit() * (1.1/12));
		addSupport(this.ohlc.getClose() - this.ohlc.getProfit() * (1.1/6));
		addSupport(this.ohlc.getClose() - this.ohlc.getProfit() * (1.1/4));
		addSupport(this.ohlc.getClose() - this.ohlc.getProfit() * (1.1/2));
		
		addResistance(this.ohlc.getProfit()*(1.1/2)+this.ohlc.getClose());
		addResistance(this.ohlc.getProfit()*(1.1/4)+this.ohlc.getClose());
		addResistance(this.ohlc.getProfit()*(1.1/6)+this.ohlc.getClose());
		addResistance(this.ohlc.getProfit()*(1.1/12)+this.ohlc.getClose());
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
		System.out.println("Support:"+values);
		this.support.add(KiteNumberUtil.round(values));
	}
	
	public void addResistance(double values) {
		System.out.println("Resistance:"+values);
		this.resistance.add(KiteNumberUtil.round(values));
	}
	
	public String name() {
		return "PP_Woodie";
	}
}
