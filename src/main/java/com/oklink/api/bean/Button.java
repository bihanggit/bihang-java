package com.oklink.api.bean;



public class Button {

	private long id;

	private long walletId;
	
	private String name = "";
	private int style;
	private String custom = "";

	private String callbackUrl = "";
	private String successUrl = "";
	
	private boolean includeName;
	private boolean includeAddress;
	private boolean includeEmail;
	private boolean includePhone;

	private Amount price;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getWalletId() {
		return walletId;
	}


	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStyle() {
		return style;
	}


	public void setStyle(int style) {
		this.style = style;
	}


	public String getCustom() {
		return custom;
	}


	public void setCustom(String custom) {
		this.custom = custom;
	}


	public String getCallbackUrl() {
		return callbackUrl;
	}


	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}


	public String getSuccessUrl() {
		return successUrl;
	}


	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}


	public Amount getPrice() {
		return price;
	}


	public void setPrice(Amount price) {
		this.price = price;
	}


	public boolean isIncludeName() {
		return includeName;
	}


	public void setIncludeName(boolean includeName) {
		this.includeName = includeName;
	}


	public boolean isIncludeAddress() {
		return includeAddress;
	}


	public void setIncludeAddress(boolean includeAddress) {
		this.includeAddress = includeAddress;
	}


	public boolean isIncludeEmail() {
		return includeEmail;
	}


	public void setIncludeEmail(boolean includeEmail) {
		this.includeEmail = includeEmail;
	}


	public boolean isIncludePhone() {
		return includePhone;
	}


	public void setIncludePhone(boolean includePhone) {
		this.includePhone = includePhone;
	}

}
