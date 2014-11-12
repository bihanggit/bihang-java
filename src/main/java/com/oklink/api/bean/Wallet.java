package com.oklink.api.bean;


public class Wallet {
	private long id;// 数据库表中记录唯一标识id
	private long userId;// 用户Id
	private String name;// 钱包名称
	private Amount btcBalance;// 非冻结bitcoin余额
	private Amount ltcBalance;// 非冻结人民币余额

	private boolean isDefault; // 是否默认 0：否 1：是
	private String btcAddress;// btc地址
	private String ltcAddress;// ltc地址
	
	private String createdAt;// 创建时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Amount getBtcBalance() {
		return btcBalance;
	}

	public void setBtcBalance(Amount btcBalance) {
		this.btcBalance = btcBalance;
	}

	public Amount getLtcBalance() {
		return ltcBalance;
	}

	public void setLtcBalance(Amount ltcBalance) {
		this.ltcBalance = ltcBalance;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getBtcAddress() {
		return btcAddress;
	}

	public void setBtcAddress(String btcAddress) {
		this.btcAddress = btcAddress;
	}

	public String getLtcAddress() {
		return ltcAddress;
	}

	public void setLtcAddress(String ltcAddress) {
		this.ltcAddress = ltcAddress;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
