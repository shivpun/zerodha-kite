package com.zerodha.kite.algo;

import java.util.ArrayList;
import java.util.List;

import com.zerodha.kite.contant.KiteFibonacci;
import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.util.KiteNumberUtil;

public class Fibonacci {

	private final OHLC ohlc;

	private List<Double> up = new ArrayList<Double>();

	private List<Double> down = new ArrayList<Double>();

	public Fibonacci(OHLC ohlc) {
		this.ohlc = ohlc;
		calculate();
	}

	protected void calculate() {
		double diff = Math.abs(this.ohlc.getProfit());
		up.add(this.ohlc.getLow());
		for (double ups : KiteFibonacci.FIBONACCI_PERCENT) {
			up.add(KiteNumberUtil.round(diff * ups + this.ohlc.getLow()));
		}
		down.add(this.ohlc.getLow());
		for (double downs : KiteFibonacci.FIBONACCI_PERCENT) {
			down.add(KiteNumberUtil.round(-diff * downs + this.ohlc.getLow()));
		}
	}

	public void display() {
		System.out.println("BUY");
		int count = -1;
		for (double ups : up) {
			if (count > -1) {
				System.out.println(ups + " | " + KiteFibonacci.FIBONACCI_PERCENT.get(count));
			} else {
				System.out.println(ups);
			}
			count = count + 1;
		}
		count = -1;
		System.out.println("SELL");
		for (double downs : down) {
			if (count > -1) {
				System.out.println(downs + " | " + KiteFibonacci.FIBONACCI_PERCENT.get(count));
			} else {
				System.out.println(downs);
			}
			count = count + 1;
		}
	}
}
