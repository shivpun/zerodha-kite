package com.zerodha.kite.algo;

import java.util.ArrayList;
import java.util.List;

import com.zerodha.kite.domain.OHLC;

public class PivotFloor {
	
	private final OHLC ohlc;
	
	private double pivot;
	
	private List<Double> support = new ArrayList<Double>();
	
	private List<Double> resistance = new ArrayList<Double>();
	
	public PivotFloor(OHLC ohlc) {
		this.ohlc = ohlc;
		this.pivot = (this.ohlc.getHigh()+this.ohlc.getLow()+this.ohlc.getClose())/3;
		this.support.add((2*this.pivot)-this.ohlc.getHigh());
		this.support.add(this.pivot-this.ohlc.getHigh()+this.ohlc.getLow());
		this.support.add(this.ohlc.getLow()-2*(this.ohlc.getHigh()+this.pivot));
		this.resistance.add((2*this.pivot)-this.ohlc.getLow());
		this.resistance.add(this.pivot+this.ohlc.getHigh()-this.ohlc.getLow());
		this.resistance.add(this.ohlc.getHigh()+2*(this.pivot-this.ohlc.getLow()));
	}
	
	public List<Double> getBuy() {
		return this.support;
	}
	
	public List<Double> getSell() {
		return this.resistance;
	}
}
