package com.oklink.api;

public class OKLinkBuilder {
	String accessToken;
	String apiKey;
	String apiSecret;

	public OKLink build() {
		return new OKLinkImpl(this);
	}

	public OKLinkBuilder withAccessToken(String access_token) {
		this.accessToken = access_token;
		return this;
	}

	public OKLinkBuilder withApiKey(String api_key, String api_secret) {
		this.apiKey = api_key;
		this.apiSecret = api_secret;
		return this;
	}
}
