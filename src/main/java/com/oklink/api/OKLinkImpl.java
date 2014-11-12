package com.oklink.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.oklink.api.bean.AddressesParams;
import com.oklink.api.bean.AddressesResponse;
import com.oklink.api.bean.Application;
import com.oklink.api.bean.ApplicationResponse;
import com.oklink.api.bean.ApplicationsResponse;
import com.oklink.api.bean.Button;
import com.oklink.api.bean.ButtonResponse;
import com.oklink.api.bean.ButtonsResponse;
import com.oklink.api.bean.ContactsParams;
import com.oklink.api.bean.ContactsResponse;
import com.oklink.api.bean.Nation;
import com.oklink.api.bean.NationsResponse;
import com.oklink.api.bean.OKLinkException;
import com.oklink.api.bean.Order;
import com.oklink.api.bean.OrderResponse;
import com.oklink.api.bean.OrdersParams;
import com.oklink.api.bean.OrdersResponse;
import com.oklink.api.bean.PageParams;
import com.oklink.api.bean.RequestParams;
import com.oklink.api.bean.Response;
import com.oklink.api.bean.SendParams;
import com.oklink.api.bean.Transaction;
import com.oklink.api.bean.TransactionResponse;
import com.oklink.api.bean.TransactionsParams;
import com.oklink.api.bean.TransactionsResponse;
import com.oklink.api.bean.User;
import com.oklink.api.bean.UserBalance;
import com.oklink.api.bean.UserBalanceResponse;
import com.oklink.api.bean.UserResponse;
import com.oklink.api.bean.Wallet;
import com.oklink.api.bean.WalletResponse;
import com.oklink.api.bean.WalletsResponse;

public class OKLinkImpl implements OKLink {
	
	public static int limit = 25;
	
	private HttpUtil httpUtil;

	public OKLinkImpl(OKLinkBuilder builder) {
		this.httpUtil = new HttpUtil(builder.apiKey, builder.apiSecret, builder.accessToken);
	}
	

	@Override
	public AddressesResponse getAddresses() throws OKLinkException, Exception {
		return getAddresses(null);
	}

	@Override
	public AddressesResponse getAddresses(AddressesParams pageParams) throws OKLinkException, Exception {
		String nationsUrl = "/api/v1/addresses";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
			params.put("query", pageParams.getQuery());
			params.put("type", pageParams.getType());
			params.put("wallet_id", pageParams.getWalletId());
		}
		return handleResponse(httpUtil.doGET(nationsUrl, params), AddressesResponse.class);
	}
	

	@Override
	public ButtonsResponse getButtons() throws OKLinkException, Exception {
		return this.getButtons(null);
	}


	@Override
	public ButtonsResponse getButtons(PageParams pageParams) throws OKLinkException, Exception {
		String buttonUrl = "/api/v1/buttons";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
		}
		return handleResponse(httpUtil.doGET(buttonUrl, params), ButtonsResponse.class);
	}
	
//	@Override
//	public Button createButton(Button button) throws OKLinkException, Exception {
//		return createButton(button, false, false, false, false);
//	}
	
	@Override
	public Button createButton(Button button) throws OKLinkException, Exception {
		
		String buttonUrl = "/api/v1/buttons";

		if(button == null) {
			throw new OKLinkException("button can't be null");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", button.getName()); 
		map.put("price", button.getPrice().getAmount());
		map.put("price_currency", button.getPrice().getCurrency());
		
		map.put("style", button.getStyle());
		map.put("wallet_id", button.getWalletId());
		map.put("custom", button.getCustom());
		
		map.put("callbackUrl", button.getCallbackUrl());
		map.put("successUrl", button.getSuccessUrl());
		
		map.put("include_name", button.isIncludeName()+"");
		map.put("include_address", button.isIncludeAddress()+"");
		map.put("include_email", button.isIncludeEmail()+"");
		map.put("include_phone", button.isIncludePhone()+"");
		
		return handleResponse(httpUtil.doPOST(buttonUrl, map), ButtonResponse.class).getButton();
	}
	
	@Override
	public Order createOrder(String buttonId) throws OKLinkException, Exception {
		return createOrder(buttonId, null, null, null, null, null, null);
	}
	
	@Override
	public Order createOrder(String buttonId, String custom, String remark) throws OKLinkException, Exception {
		return createOrder(buttonId, custom, remark, null, null, null, null);
	}
	
	@Override
	public Order createOrder(String buttonId, String custom, String remark, String clientName, String clientAddress, 
				String clientEmail, String clientPhone)	throws OKLinkException, Exception {
		
//		if(buttonId <= 0) {
//			throw new OKLinkException("Button  with id("+buttonId+") does not exists");
//		}
		
		String createOrderUrl = "/api/v1/buttons/"+ buttonId +"/create_order";
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(!OKLinkImpl.isEmpty(clientName)) {
			map.put("client_name", clientName);
		}
		if(!OKLinkImpl.isEmpty(clientAddress)) {
			map.put("client_address", clientAddress);
		}
		if(!OKLinkImpl.isEmpty(clientEmail)) {
			map.put("client_email", clientEmail);
		}
		if(!OKLinkImpl.isEmpty(clientPhone)) {
			map.put("client_phone", clientPhone);
		}
		map.put("custom", custom);
		map.put("remark", remark);
		return handleResponse(httpUtil.doPOST(createOrderUrl, map), OrderResponse.class).getOrder();
	}
	
	@Override
	public OrdersResponse getOrders(String buttonId) throws OKLinkException, Exception {
		return getOrders(buttonId, null);
	}
	
	@Override
	public OrdersResponse getOrders(String buttonId, PageParams pageParams) throws OKLinkException, Exception {
//		if(buttonId<=0) {
//			throw new OKLinkException("Button  with id("+buttonId+") does not exists");
//		}
		String getOrderUrl = "/api/v1/buttons/"+buttonId+"/orders";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
		}
		return handleResponse(httpUtil.doGET(getOrderUrl, params), OrdersResponse.class);
	}
	
	@Override
	public List<Nation> getNations() throws OKLinkException, Exception {
		return getNations(null);
	}

	@Override
	public List<Nation> getNations(String query) throws OKLinkException, Exception {
		String nationsUrl = "/api/v1/nations";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("query", query);
		return handleResponse(httpUtil.doGET(nationsUrl, params), NationsResponse.class).getNations();
	}
	
	@Override
	public ContactsResponse getContacts() throws OKLinkException, Exception {
		return getContacts(null);
	}

	@Override
	public ContactsResponse getContacts(ContactsParams contactsParams) throws OKLinkException, Exception {
		String nationsUrl = "/api/v1/contacts";
		Map<String, Object> params = new HashMap<String, Object>();
		if(contactsParams != null) {
			params.put("page", contactsParams.getPage());
			params.put("limit", contactsParams.getLimit());
			params.put("query", contactsParams.getQuery());
			params.put("type", contactsParams.getType());
		}
		return handleResponse(httpUtil.doGET(nationsUrl, params), ContactsResponse.class);
	}
	
	@Override
	public User getUser() throws OKLinkException, Exception {
		String usersUrl = "/api/v1/users";
		return handleResponse(httpUtil.doGET(usersUrl, null), UserResponse.class).getUser();
	}
	
	@Override
	public UserBalance getUserBalance() throws OKLinkException, Exception {
		String usersUrl = "/api/v1/users/balance";
		return handleResponse(httpUtil.doGET(usersUrl, null), UserBalanceResponse.class).getUser();
	}
	
	@Override
	public Application getApplication(long applicationId) throws OKLinkException, Exception {
		
		if(applicationId<=0) {
			throw new OKLinkException("Application  with id("+applicationId+") does not exists");
		}
		
		String detailApplicationUrl = "/api/v1/oauth/applications/" + applicationId;
		
		return handleResponse(httpUtil.doGET(detailApplicationUrl, null), ApplicationResponse.class).getApplication();
	}
	
	@Override
	public ApplicationsResponse getApplications(PageParams pageParams) throws OKLinkException, Exception {
		String oauthUrl = "/api/v1/oauth/applications";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
		}
		return handleResponse(httpUtil.doGET(oauthUrl, params), ApplicationsResponse.class);
	}
	
	@Override
	public Application createApplication(Application application) throws OKLinkException, Exception {
		if(application == null || OKLinkImpl.isEmpty(application.getName()) 
							|| OKLinkImpl.isEmpty(application.getRedirectUri()) || !application.getRedirectUri().startsWith("http")) {
			throw new OKLinkException("name、redirectUri field of application is not empty and redirectUri must start with 'http' ");
		}
		
		String oauthUrl = "/api/v1/oauth/applications";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", application.getName());
		map.put("redirect_uri", application.getRedirectUri());
		return handleResponse(httpUtil.doPOST(oauthUrl, map), ApplicationResponse.class).getApplication();
		
	}
	
	@Override
	public Order getOrder(long orderId) throws OKLinkException, Exception {
		if(orderId<=0) {
			throw new OKLinkException("Order  with id("+orderId+") does not exists");
		}
		String orderUrl = "/api/v1/orders/" + orderId;
		return handleResponse(httpUtil.doGET(orderUrl, null), OrderResponse.class).getOrder();
	}
	
	@Override
	public OrdersResponse getOrders() throws OKLinkException, Exception {
		return getOrders(new OrdersParams());
	}
	
	@Override
	public OrdersResponse getOrders(OrdersParams ordersParams) throws OKLinkException, Exception {
		String orderUrl = "/api/v1/orders";
		Map<String, Object> params = new HashMap<String, Object>();
		if(ordersParams != null) {
			params.put("page", ordersParams.getPage());
			params.put("limit", ordersParams.getLimit());
			params.put("wallet_id", ordersParams.getWalletId());
		}
		return handleResponse(httpUtil.doGET(orderUrl, params), OrdersResponse.class);
	}
	
	
	@Override
	public Order createOrder(Button button) throws OKLinkException, Exception {
		return createOrder(button, null, null, null, null, null, null);
	}
	
	@Override
	public Order createOrder(Button button, String custom, String remark) throws OKLinkException, Exception {
		return createOrder(button, custom, remark, null, null, null, null);
	}
	
	@Override
	public Order createOrder(Button button, String custom, String remark, String clientName,String clientAddress, String clientEmail, String clientPhone)
			throws OKLinkException, Exception {
		
		if(button == null) {
			throw new OKLinkException("button can't be null");
		}
		boolean flag = false;
		String message = "[";
		if(button.isIncludeAddress() && OKLinkImpl.isEmpty(clientAddress)) {
			flag = true;
			message += "clientAddress,";
		}
		if(button.isIncludeEmail() && OKLinkImpl.isEmpty(clientEmail)) {
			flag = true;
			message += "clientEmail,";
		}
		if(button.isIncludeName() && OKLinkImpl.isEmpty(clientName)) {
			flag = true;
			message += "clientName,";
		}
		if(button.isIncludePhone() && OKLinkImpl.isEmpty(clientPhone)) {
			flag = true;
			message += "clientPhone,";
		}
		if(message.endsWith(",")) {
			message = message.substring(0, message.length()-1);
		}
		if(flag) {
			throw new OKLinkException("Create Order for this button requires user information" + message);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", button.getName()); 
		map.put("price", button.getPrice().getAmount());
		map.put("price_currency", button.getPrice().getCurrency());
		
		map.put("style", button.getStyle());
		map.put("wallet_id", button.getWalletId());
		map.put("custom", button.getCustom());
		
		map.put("callbackUrl", button.getCallbackUrl());
		map.put("successUrl", button.getSuccessUrl());
		
		map.put("include_name", button.isIncludeName()+"");
		map.put("include_address", button.isIncludeAddress()+"");
		map.put("include_email", button.isIncludeEmail()+"");
		map.put("include_phone", button.isIncludePhone()+"");
		
		map.put("custom", custom);
		map.put("remark", remark);
		
		String orderUrl = "/api/v1/orders";
		
		return handleResponse(httpUtil.doPOST(orderUrl, map), OrderResponse.class).getOrder();
	}
	
	@Override
	public Wallet getDefaultWallet() throws OKLinkException, Exception {
		String walletUrl = "/api/v1/wallets/default";
		return handleResponse(httpUtil.doGET(walletUrl, null), WalletResponse.class).getWallet();
	}
	
	@Override
	public List<Wallet> getWallets() throws OKLinkException, Exception {
		return getWallets(null);
	}
	
	@Override
	public List<Wallet> getWallets(String query) throws OKLinkException, Exception {
		String walletUrl = "/api/v1/wallets";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(!OKLinkImpl.isEmpty(query)) {
			map.put("query", query);
		}
		
		return handleResponse(httpUtil.doGET(walletUrl, map), WalletsResponse.class).getWallets();
	}
	
	@Override
	public Wallet createWallet(String name) throws OKLinkException, Exception {
		String walletUrl = "/api/v1/wallets";
		
		if(OKLinkImpl.isEmpty(name)) {
			throw new OKLinkException("name can't be null");
		}
		String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]{1,10}$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
		if(!m.matches()){
			throw new OKLinkException("名称长度1-10，并且不包含特殊字符");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		
		return handleResponse(httpUtil.doPOST(walletUrl, map), WalletResponse.class).getWallet();
	}
	
	@Override
	public Wallet setDefault(long walletId) throws OKLinkException, Exception {

		if(walletId <= 0) {
			throw new OKLinkException("Wallet  with id("+walletId+") does not exists");
		}
		String walletUrl = "/api/v1/wallets/" + walletId + "/default";
		
		return handleResponse(httpUtil.doPUT(walletUrl, null), WalletResponse.class).getWallet();
	}
	
	@Override
	public Wallet updateWallet(long walletId, String name) throws OKLinkException, Exception {

		if(walletId <= 0) {
			throw new OKLinkException("Wallet  with id("+walletId+") does not exists");
		}
		if(OKLinkImpl.isEmpty(name)) {
			throw new OKLinkException("name can't be null");
		}
		String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]{1,10}$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
		if(!m.matches()){
			throw new OKLinkException("name length should between 1-10");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		
		String walletUrl = "/api/v1/wallets/" + walletId + "/update";
		
		return handleResponse(httpUtil.doPUT(walletUrl, map), WalletResponse.class).getWallet();
	}
	
	@Override
	public Wallet deleteWallet(long walletId) throws OKLinkException, Exception {

		if(walletId <= 0) {
			throw new OKLinkException("Wallet  with id("+walletId+") does not exists");
		}
		
		String walletUrl = "/api/v1/wallets/144/delete";
		
		return handleResponse(httpUtil.doPUT(walletUrl, null), WalletResponse.class).getWallet();
	}
	
	@Override
	public Transaction getTransaction(long transactionId) throws OKLinkException, Exception {
		if(transactionId <= 0) {
			throw new OKLinkException("Transaction  with id("+transactionId+") does not exists");
		}
		String transactionUrl = "/api/v1/transactions/" + transactionId;
		
		return handleResponse(httpUtil.doGET(transactionUrl, null), TransactionResponse.class).getTransaction();
	}
	
	@Override
	public TransactionsResponse getTransactions() throws OKLinkException,Exception {
		return getTransactions(null);
	}
	
	@Override
	public TransactionsResponse getTransactions(TransactionsParams transactionsParams) throws OKLinkException,
			Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(transactionsParams != null) {
			map.put("walletId", transactionsParams.getWalletId());
			map.put("page", transactionsParams.getPage());
			map.put("limit", transactionsParams.getLimit());
		}
		
		String transactionUrl = "/api/v1/transactions";
		
		return handleResponse(httpUtil.doGET(transactionUrl, map), TransactionsResponse.class);
	}
	
	@Override
	public Transaction sendMoney(SendParams sendParams) throws OKLinkException, Exception {
		if(sendParams == null) {
			throw new OKLinkException("params missing!!!");
		}
		sendParams.check();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("to", sendParams.getTo());
		map.put("target_type", sendParams.getTargetType()); 
		map.put("amount", sendParams.getAmount().getAmount());
		map.put("currency_type", "BTC".equals(sendParams.getAmount().getCurrency())?0:1);
		map.put("wallet_id", sendParams.getWalletId());
		map.put("offchain", sendParams.isOffchain()?0:1);
		map.put("notes", sendParams.getNotes());
		if(sendParams.getTargetType() == 2) {
			map.put("area_code", sendParams.getAmount());
		}
		String transactionUrl = "/api/v1/transactions/send_money";
		
		return handleResponse(httpUtil.doPUT(transactionUrl, map), TransactionResponse.class).getTransaction();
	}
	
	@Override
	public Transaction requestMoney(RequestParams requestParams) throws OKLinkException, Exception {
		
		if(requestParams == null) {
			throw new OKLinkException("params missing!!!");
		}
		
		requestParams.check();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("from", requestParams.getFrom());
		map.put("target_type", requestParams.getTargetType()); //0email 1phone 2remark 
		map.put("amount", requestParams.getAmount().getAmount());
		map.put("currency_type", "BTC".equals(requestParams.getAmount().getCurrency())?0:1);
		map.put("wallet_id", requestParams.getWalletId());
		if(requestParams.getTargetType() == 1) {
			map.put("area_code", requestParams.getAreaCode());
		}
//		doGET("/api/v1/transactions/simple", map);
//		ApiUtil.doPUT("/api/v1/transactions/request_money", map);
		String transactionUrl = "/api/v1/transactions/request_money";
		return handleResponse(httpUtil.doPUT(transactionUrl, map), TransactionResponse.class).getTransaction();
	}	

	public <T extends Response> T handleResponse(String text, Class<T> clazz) throws OKLinkException {
		try {
			//text = StringUtil.changeUnderline2Hump(text);
			
			T response = OKLinkImpl.parseJSON(text, clazz);
			if(response.getSuccess() != null && "false".equals(response.getSuccess())) {
				throw new OKLinkException(response.getError());
			}
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OKLinkException(e.getMessage());
		}
	}
	
	private static <T extends Object> T parseJSON(String text, Class<T> clazz) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T o = mapper.readValue(text, clazz);

		return o;
	}
	
	private static boolean isEmpty(String str) {
		if(str == null) 
			return true; 
		String tempStr = str.trim(); 
		if(tempStr.length() == 0)
			return true; 
		if(tempStr.equals("null"))
			return true;
		return false; 
	}
	
	public static void main(String[] args) {
		System.out.println("abc".substring(0, "abc".length()-1));
	}

}
