package com.oklink.api.bean;

public class Amount {
	private double amount;
	private String currency;

	public Amount() {
	}
	
	public Amount(double amount, CoinType coinType) {
		this.amount = amount;
		this.currency = coinType.name();
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
