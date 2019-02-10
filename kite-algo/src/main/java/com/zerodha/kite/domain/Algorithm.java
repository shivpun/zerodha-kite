package com.zerodha.kite.domain;

import java.io.Serializable;
import java.util.Date;

import com.zerodha.kite.algo.Algo;

public class Algorithm implements Serializable {

	private static final long serialVersionUID = 3711694103728264248L;
	
	private String name;
	
	private Date chartDate;
	
	private String timeStamp;
	
	private double token;
	
	private Algo algo;
	
	private double values;
	
	private Integer timeFrame = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getChartDate() {
		return chartDate;
	}

	public void setChartDate(Date chartDate) {
		this.chartDate = chartDate;
	}

	public double getToken() {
		return token;
	}

	public void setToken(double token) {
		this.token = token;
	}
	
	public void cal(double values) {
		this.values = getAlgo().cal(values);
	}
	
	public double values() {
		return this.values;
	}
	
	public Algo getAlgo() {
		return algo;
	}

	public void setAlgo(Algo algo) {
		this.algo = algo;
	}

	public Integer getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(Integer timeFrame) {
		if(timeFrame!=null) {
			this.timeFrame = timeFrame;
		}
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Algorithm create() {
		Algorithm algorithm = new Algorithm();
		algorithm.setChartDate(getChartDate());
		algorithm.setName(getName());
		algorithm.setTimeFrame(getTimeFrame());
		algorithm.setToken(getToken());
		algorithm.setAlgo(getAlgo());
		algorithm.setTimeStamp(getTimeStamp());
		return algorithm;
	}

	@Override
	public String toString() {
		return "Algorithm [name=" + name + ", chartDate=" + chartDate + ", token=" + token + ", timeFrame=" + timeFrame
				+ "]";
	}
}
