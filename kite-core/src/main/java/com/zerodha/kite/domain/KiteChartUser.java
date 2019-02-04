package com.zerodha.kite.domain;

import java.io.Serializable;

public class KiteChartUser implements Serializable {

	private static final long serialVersionUID = 3108884767396389960L;
	
	private String userId;
	
	private Double token;
	
	private Integer timeFrame;
	
	private String publicToken;
	
	private String accessToken;
	
	private String toDate;
	
	private String fromDate;
	
	private String cookie;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getToken() {
		return token;
	}

	public void setToken(Double token) {
		this.token = token;
	}

	public Integer getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(Integer timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getPublicToken() {
		return publicToken;
	}

	public void setPublicToken(String publicToken) {
		this.publicToken = publicToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@Override
	public String toString() {
		return "KiteChartUser [userId=" + userId + ", token=" + token + ", timeFrame=" + timeFrame + ", publicToken="
				+ publicToken + ", accessToken=" + accessToken + ", toDate=" + toDate + ", fromDate=" + fromDate
				+ ", cookie=" + cookie + "]";
	}
}
