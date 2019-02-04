package com.zerodha.kite.domain;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChartData implements Serializable {

	private static final long serialVersionUID = 7062209596929132785L;
	
	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "data")
	private Map<String, Object> data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
