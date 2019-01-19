package com.zerodha.kite.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * A wrapper for market depth.
 */
public class Depth {

	@JsonProperty(value = "quantity")
	@SerializedName(value = "quantity")
	private int quantity;

	@JsonProperty(value = "price")
	@SerializedName(value = "price")
	private double price;

	@JsonProperty(value = "orders")
	@SerializedName(value = "orders")
	private int orders;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Depth [quantity=" + quantity + ", price=" + price + ", orders=" + orders + "]";
	}
}