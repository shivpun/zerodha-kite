package com.zerodha.kite.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.zerodha.kite.domain.OHLC;

@DisplayName("Pivot Point Calculator")
public class PivotPointTest {
	
	@Test
	@DisplayName(value="Pivot Point Floor")
	public void Pivot_Floor() {
		PivotFloor floor = new PivotFloor(ohlc());
		int count = 0;
		for(double support:supFloor()) {
			assertEquals(support, floor.getBuy().get(count));
			count++;
		}
		count = 0;
		for(double resistance:resFloor()) {
			assertEquals(resistance, floor.getSell().get(count));
			count++;
		}
		
	}
	
	@Test
	@DisplayName(value="Pivot Point Woodie")
	public void Pivot_Woodie() {
		PivotWoodie floor = new PivotWoodie(ohlc());
		int count = 0;
		for(double support:supWoodie()) {
			assertEquals(support, floor.getBuy().get(count));
			count++;
		}
		count = 0;
		for(double resistance:resWoodie()) {
			assertEquals(resistance, floor.getSell().get(count));
			count++;
		}
	}
	
	public OHLC ohlc() {
		OHLC ohlc = new OHLC();
		ohlc.setOpen(2119.40);
		ohlc.setClose(2115);
		ohlc.setHigh(2127.95);
		ohlc.setLow(2115);
		return ohlc;
	}
	
	public List<Double> supFloor() {
		List<Double> support = new ArrayList<Double>();
		support.add(2110.68);
		support.add(2106.37);
		support.add(2097.73);
		return support;
	}
	
	public List<Double> resFloor() {
		List<Double> resistance = new ArrayList<Double>();
		resistance.add(2123.63);
		resistance.add(2132.27);
		resistance.add(2136.58);
		return resistance;
	} 
	
	public List<Double> supWoodie() {
		List<Double> support = new ArrayList<Double>();
		support.add(2108.53);
		support.add(2105.29);
		//support.add(2097.73);
		return support;
	}
	
	public List<Double> resWoodie() {
		List<Double> resistance = new ArrayList<Double>();
		resistance.add(2121.48);
		resistance.add(2131.19);
		return resistance;
	} 
}
