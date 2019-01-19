package com.zerodha.kite.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * A wrapper for tick.
 */

public class Tick {

	@JsonProperty(value = "mode")
	@SerializedName(value = "mode")
	private String mode;

	@JsonProperty(value = "tradable")
	@SerializedName(value = "tradable")
	private boolean tradable;

	@JsonProperty(value = "token")
	@SerializedName(value = "token")
	private long instrumentToken;

	@JsonProperty(value = "lastTradedPrice")
	@SerializedName(value = "lastTradedPrice")
	private double lastTradedPrice;

	@JsonProperty(value = "highPrice")
	@SerializedName(value = "highPrice")
	private double highPrice;

	@JsonProperty(value = "lowPrice")
	@SerializedName(value = "lowPrice")
	private double lowPrice;

	@JsonProperty(value = "openPrice")
	@SerializedName(value = "openPrice")
	private double openPrice;

	@JsonProperty(value = "closePrice")
	@SerializedName(value = "closePrice")
	private double closePrice;

	@JsonProperty(value = "change")
	@SerializedName(value = "change")
	private double change;

	@JsonProperty(value = "lastTradeQuantity")
	@SerializedName(value = "lastTradeQuantity")
	private double lastTradedQuantity;

	@JsonProperty(value = "averageTradePrice")
	@SerializedName(value = "averageTradePrice")
	private double averageTradePrice;

	@JsonProperty(value = "volumeTradedToday")
	@SerializedName(value = "volumeTradedToday")
	private double volumeTradedToday;

	@JsonProperty(value = "totalBuyQuantity")
	@SerializedName(value = "totalBuyQuantity")
	private double totalBuyQuantity;

	@JsonProperty(value = "totalSellQuantity")
	@SerializedName(value = "totalSellQuantity")
	private double totalSellQuantity;

	@JsonProperty(value = "lastTradedTime")
	@SerializedName(value = "lastTradedTime")
	private Date lastTradedTime;

	@JsonProperty(value = "oi")
	@SerializedName(value = "oi")
	private double oi;

	@JsonProperty(value = "openInterestDayHigh")
	@SerializedName(value = "openInterestDayHigh")
	private double oiDayHigh;

	@JsonProperty(value = "openInterestDayLow")
	@SerializedName(value = "openInterestDayLow")
	private double oiDayLow;

	@JsonProperty(value = "tickTimestamp")
	@SerializedName(value = "tickTimestamp")
	private Date tickTimestamp;

	@JsonProperty(value = "depth")
	@SerializedName(value = "depth")
	private Map<String, List<Depth>> depth;

	public double getOiDayHigh() {
		return oiDayHigh;
	}

	public void setOiDayHigh(double oiDayHigh) {
		this.oiDayHigh = oiDayHigh;
	}

	public double getOiDayLow() {
		return oiDayLow;
	}

	public void setOiDayLow(double oiDayLow) {
		this.oiDayLow = oiDayLow;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public Date getLastTradedTime() {
		return lastTradedTime;
	}

	public void setLastTradedTime(Date lastTradedTime) {
		this.lastTradedTime = lastTradedTime;
	}

	public double getOi() {
		return oi;
	}

	public void setOi(double oi) {
		this.oi = oi;
	}

	public double getOpenInterestDayHigh() {
		return oiDayHigh;
	}

	public void setOpenInterestDayHigh(double dayHighOpenInterest) {
		this.oiDayHigh = dayHighOpenInterest;
	}

	public double getOpenInterestDayLow() {
		return oiDayLow;
	}

	public void setOpenInterestDayLow(double dayLowOpenInterest) {
		this.oiDayLow = dayLowOpenInterest;
	}

	public Date getTickTimestamp() {
		return tickTimestamp;
	}

	public void setTickTimestamp(Date tickTimestamp) {
		this.tickTimestamp = tickTimestamp;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isTradable() {
		return tradable;
	}

	public void setTradable(boolean tradable) {
		this.tradable = tradable;
	}

	public long getInstrumentToken() {
		return instrumentToken;
	}

	public void setInstrumentToken(long token) {
		this.instrumentToken = token;
	}

	public double getLastTradedPrice() {
		return lastTradedPrice;
	}

	public void setLastTradedPrice(double lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getChange() {
		return change;
	}

	public void setNetPriceChangeFromClosingPrice(double netPriceChangeFromClosingPrice) {
		this.change = netPriceChangeFromClosingPrice;
	}

	public double getLastTradedQuantity() {
		return lastTradedQuantity;
	}

	public void setLastTradedQuantity(double lastTradedQuantity) {
		this.lastTradedQuantity = lastTradedQuantity;
	}

	public double getAverageTradePrice() {
		return averageTradePrice;
	}

	public void setAverageTradePrice(double averageTradePrice) {
		this.averageTradePrice = averageTradePrice;
	}

	public double getVolumeTradedToday() {
		return volumeTradedToday;
	}

	public void setVolumeTradedToday(double volumeTradedToday) {
		this.volumeTradedToday = volumeTradedToday;
	}

	public double getTotalBuyQuantity() {
		return totalBuyQuantity;
	}

	public void setTotalBuyQuantity(double totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
	}

	public double getTotalSellQuantity() {
		return totalSellQuantity;
	}

	public void setTotalSellQuantity(double totalSellQuantity) {
		this.totalSellQuantity = totalSellQuantity;
	}

	public Map<String, List<Depth>> getMarketDepth() {
		return depth;
	}

	public void setMarketDepth(Map<String, List<Depth>> marketDepth) {
		this.depth = marketDepth;
	}

	@Override
	public String toString() {
		return "Tick [mode=" + mode + ", tradable=" + tradable + ", instrumentToken=" + instrumentToken
				+ ", lastTradedPrice=" + lastTradedPrice + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice
				+ ", openPrice=" + openPrice + ", closePrice=" + closePrice + ", change=" + change
				+ ", lastTradedQuantity=" + lastTradedQuantity + ", averageTradePrice=" + averageTradePrice
				+ ", volumeTradedToday=" + volumeTradedToday + ", totalBuyQuantity=" + totalBuyQuantity
				+ ", totalSellQuantity=" + totalSellQuantity + ", lastTradedTime=" + lastTradedTime + ", oi=" + oi
				+ ", oiDayHigh=" + oiDayHigh + ", oiDayLow=" + oiDayLow + ", tickTimestamp=" + tickTimestamp
				+ ", depth=" + depth + "]";
	}
}
