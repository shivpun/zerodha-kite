package com.zerodha.kite.domain;

import java.io.Serializable;

public class KiteUser implements Serializable {

	private static final long serialVersionUID = -6520363990110935169L;

	private String userId;
	
	private String cookie;
	
	private String publicToken;
	
	private String accessToken;
	
	private String sessionId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
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
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof KiteUser)) {
			return false;
		}
		KiteUser other = (KiteUser) obj;
		return equalToUserId(other);
	}

	private boolean equalToUserId(KiteUser other) {
		if (this.userId == null || other.userId == null) {
			return false;
		}
		return this.userId.equals(other.userId);
	}

	@Override
	public String toString() {
		return "KiteUser [userId=" + userId + ", cookie=" + cookie + ", publicToken=" + publicToken + ", accessToken="
				+ accessToken + ", sessionId=" + sessionId + "]";
	}
}
