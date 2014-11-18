package com.oklink.api;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oklink.api.bean.AddressesParams;
import com.oklink.api.bean.AddressesResponse;
import com.oklink.api.bean.Amount;
import com.oklink.api.bean.Application;
import com.oklink.api.bean.ApplicationsResponse;
import com.oklink.api.bean.Button;
import com.oklink.api.bean.ButtonsResponse;
import com.oklink.api.bean.CoinType;
import com.oklink.api.bean.ContactsParams;
import com.oklink.api.bean.ContactsResponse;
import com.oklink.api.bean.Nation;
import com.oklink.api.bean.Order;
import com.oklink.api.bean.OrdersResponse;
import com.oklink.api.bean.PageParams;
import com.oklink.api.bean.RequestParams;
import com.oklink.api.bean.SendParams;
import com.oklink.api.bean.Transaction;
import com.oklink.api.bean.TransactionsParams;
import com.oklink.api.bean.TransactionsResponse;
import com.oklink.api.bean.User;
import com.oklink.api.bean.UserBalance;
import com.oklink.api.bean.Wallet;

/**
 * Hello world!
 * 
 */
public class App {
	
	// localtest gengchj
	static String API_KEY = "3371414643510980114";
	static String API_SECRET = "58CB531175947E40487080B4166C3759";
	
	// localtest kastiny
//	static String API_KEY = "5B536D218C7532126D6A9CF10ECD010B";
//	static String API_SECRET = "3E99D7A11AD7FD0827358F4673851AE9";
	
	//www.oklink.com kastiny
//	static String API_KEY = "1151414741802882121"; 
//	static String API_SECRET = "64B531E59CBE74DF0D2F6CE9CFC4C962";
	
	// online
//	static String API_KEY = "CDF0B02BC2A34840A5CED3A7CD6BD504"; 
//	static String API_SECRET = "C22E5D6B46D49FE35EED717F9767EB6F";
	
	OKLink ol = null;
	ObjectMapper mapper = null;

	@Before
	public void init() throws Exception {
		ol = new OKLinkBuilder().withApiKey(API_KEY, API_SECRET)
				.setHost("http://localhost")
				.build();
//		ol = new OKLinkBuilder().withAccessToken("14e94a004c0b8e409612f6a3f87ad6e206f1e4fd4ead69ec87d61523f352b9eb")
//				.setHost("http://localhost").build();
		mapper = new ObjectMapper();
	}
	
	@Test
	public void getAddress() throws Exception {
		AddressesParams addressesParams = new AddressesParams();
		addressesParams.setWalletId(144);
		AddressesResponse response = ol.getAddresses(addressesParams);
		printInfo(response);
	}
	
	@Test
	public void getButtons() throws Exception {
		PageParams params = new PageParams();
		params.setPage(1);
		ButtonsResponse response = ol.getButtons(params);
		printInfo(response);
	}
	
	@Test
	public void createButton() throws Exception {
		Button button = new Button();
		button.setName("hehetest");
		button.setPrice(new Amount(0.001, CoinType.BTC));
		
		Button button2 = ol.createButton(button);
		
		printInfo(button2);
	}
	
	@Test
	public void createOrder() throws Exception {
		Order order = ol.createOrder("bdd9abf7222e43bab68de5a629a1e8b6");
		printInfo(order);
	}
	
	@Test
	public void getOrders() throws Exception {
		OrdersResponse response = ol.getOrders("bdd9abf7222e43bab68de5a629a1e8b6");
		printInfo(response);
	}
	
	@Test
	public void getUser() throws Exception {
		User user = ol.getUser();
		System.out.println(mapper.writeValueAsString(user));
	}
	
	@Test
	public void getUserBalance() throws Exception {
		UserBalance user = ol.getUserBalance();
		System.out.println(mapper.writeValueAsString(user));
		for(Wallet wallet : user.getWalletBalances()) {
			System.out.println(wallet.getBtcBalance() + ", " + (wallet.getBtcBalance().getAmount().doubleValue() + 10));
		}
	}
	
	@Test
	public void getContacts() throws Exception {
		ContactsParams contactsParams = new ContactsParams();
		contactsParams.setPage(1);
		contactsParams.setType(1);
		contactsParams.setLimit(3);
		ContactsResponse response = ol.getContacts(contactsParams);
		printInfo(response);
	}
	
	@Test
	public void getNations() throws Exception {
		
		List<Nation> nations = ol.getNations("中");
		System.out.println(mapper.writeValueAsString(nations));
	}
	
	
	@Test
	public void getApplication() throws Exception {
		Application application = ol.getApplication(1129);
		printInfo(application);
	}
	
	@Test
	public void getApplications() throws Exception {
		PageParams params = new PageParams();
		ApplicationsResponse response = ol.getApplications(params);
		printInfo(response);
	}
	
	@Test
	public void createApplication() throws Exception {
		Application application = new Application();
		application.setName("from_api");
		application.setRedirectUri("http://www.abc.cn");
		application = ol.createApplication(application);
		printInfo(application);
	}
	
	@Test
	public void getDefaultWallet() throws Exception {
		Wallet wallet = ol.getDefaultWallet();
		printInfo(wallet);
	}
	@Test
	public void getWallets() throws Exception {
		List<Wallet> wallets = ol.getWallets();
		printInfo(wallets);
	}
	@Test
	public void createWallet() throws Exception {
		Wallet wallet = ol.createWallet("我们说好的一起");
		printInfo(wallet);
	}
	@Test
	public void updateWallet() throws Exception {
		Wallet wallet = ol.updateWallet(263, "updateapi");
		printInfo(wallet);
	}
	@Test
	public void deleteWallet() throws Exception {
		Wallet wallet = ol.deleteWallet(1);
		printInfo(wallet);
	}
	@Test
	public void setDefaultWallet() throws Exception {
		Wallet wallet = ol.setDefault(263);
		printInfo(wallet);
	}
	
	@Test
	public void getTransaction() throws Exception {
		Transaction transaction = ol.getTransaction(585);
		printInfo(transaction);
	}
	@Test
	public void getTransactions() throws Exception {
		TransactionsParams params = new TransactionsParams();
		params.setLimit(3);
		TransactionsResponse response = ol.getTransactions(params);
		printInfo(response);
	}
	
	@Test
	public void sendMoney() throws Exception {
		SendParams params = new SendParams();
		params.setToEmail("gengchj@foxmail.com");
		params.setAmount(new Amount(0.001, CoinType.LTC));
		params.setOffchain(false);
		Transaction transaction = ol.sendMoney(params);
		printInfo(transaction);
	}
	
	@Test
	public void requestMoney() throws Exception {
		RequestParams params = new RequestParams();
//		params.setFromEmail("gengchj@foxmail.com");
		params.setFromPhone("18511101171", "86");
		params.setAmount(new Amount(0.001, CoinType.BTC));
		Transaction transaction = ol.requestMoney(params);
		printInfo(transaction);
	}
	
	private void printInfo(Object obj) throws Exception {
		System.out.println(mapper.writeValueAsString(obj));
	}
	
}
