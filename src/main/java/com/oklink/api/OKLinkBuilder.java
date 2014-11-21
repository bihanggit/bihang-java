package com.oklink.api;

public class OKLinkBuilder {
	String accessToken;
	String apiKey;
	String apiSecret;
	String host;
	
	private static OKLinkBuilder instance;
	
	private OKLinkBuilder() {}
	
	public static synchronized OKLinkBuilder getInstance() {
		if(instance == null) {
			instance = new OKLinkBuilder();
		}
		return instance;
	}
	
	public OKLink buildWithAccessToken(String access_token) {
		this.accessToken = access_token;
		return new OKLinkImpl(this);
	}
	
	public OKLink buildWithApiKey(String api_key, String api_secret) {
		this.apiKey = api_key;
		this.apiSecret = api_secret;
		return new OKLinkImpl(this);
	}

	public OKLinkBuilder setHost(String host) {
		this.host = host;
		return this;
	}
}
