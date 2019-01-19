package com.zerodha.kite.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KiteMessage implements Serializable {

	private static final long serialVersionUID = -5735645080100184264L;
	
	private KiteUser kiteUser;
	
	private Map<String, String> headers = new HashMap<String, String>();
	
	private List<String> messages = new ArrayList<String>();

	public List<String> getMessage() {
		return messages;
	}

	public void setMessage(List<String> messages) {
		this.messages = messages;
	}
	
	public void addMessage(String message) {
		this.messages.add(message);
	}

	public KiteUser getKiteUser() {
		return kiteUser;
	}

	public void setKiteUser(KiteUser kiteUser) {
		this.kiteUser = kiteUser;
	}
	
	public void addHeader(String name, String value) {
		this.headers.put(name, value);
	}
	
	public Map<String, String> getHeader() {
		return this.headers;
	}
}
