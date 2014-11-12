package com.oklink.api;

import java.util.List;

import com.oklink.api.bean.AddressesParams;
import com.oklink.api.bean.AddressesResponse;
import com.oklink.api.bean.Application;
import com.oklink.api.bean.ApplicationsResponse;
import com.oklink.api.bean.Button;
import com.oklink.api.bean.ButtonsResponse;
import com.oklink.api.bean.ContactsParams;
import com.oklink.api.bean.ContactsResponse;
import com.oklink.api.bean.Nation;
import com.oklink.api.bean.OKLinkException;
import com.oklink.api.bean.Order;
import com.oklink.api.bean.OrdersParams;
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

public interface OKLink {
	
	/**
	 * 获取用户账户关联地址 
	 */
	public AddressesResponse getAddresses() throws OKLinkException, Exception;
	
	public AddressesResponse getAddresses(AddressesParams addressesParams) throws OKLinkException, Exception;
	
	/**
	 * 获取用户创建的button
	 */
	public ButtonsResponse getButtons() throws OKLinkException, Exception;
	
	public ButtonsResponse getButtons(PageParams pageParams) throws OKLinkException, Exception;
	
	/**
	 * 创建button
	 */
	public Button createButton(Button button) throws OKLinkException, Exception;
	
	/**
	 * 获取某button关联的order
	 */
	public OrdersResponse getOrders(String buttonId) throws OKLinkException, Exception;
	
	public OrdersResponse getOrders(String buttonId, PageParams pageParams) throws OKLinkException, Exception;
	
	/**
	 * 为某button创建order 
	 */
	public Order createOrder(String buttonId) throws OKLinkException, Exception;
	
	/**
	 * 为某button创建order 
	 */
	public Order createOrder(String buttonId, String custom, String remark) throws OKLinkException, Exception;
	
	/**
	 * 为某button创建order，添加用户信息
	 */
	public Order createOrder(String buttonId, String custom, String remark, String clientName, String clientAddress,String clientEmail, String clientPhone) throws OKLinkException, Exception;
	
	/**
	 * 获取联系人
	 */
	public ContactsResponse getContacts() throws OKLinkException, Exception;
	
	public ContactsResponse getContacts(ContactsParams params) throws OKLinkException, Exception;
	
	/**
	 * 获取国家编码
	 */
	public List<Nation> getNations() throws OKLinkException, Exception;
	
	public List<Nation> getNations(String query) throws OKLinkException, Exception;

	/**
	 * 获取应用信息
	 */
	public Application getApplication(long applicationId) throws OKLinkException, Exception;
	
	public ApplicationsResponse getApplications(PageParams pageParams) throws OKLinkException, Exception;
	
	/**
	 * 创建应用
	 */
	public Application createApplication(Application application) throws OKLinkException, Exception;
	
	/**
	 * 列出当前用户的order列表
	 */
	public OrdersResponse getOrders() throws OKLinkException, Exception;
	
	public OrdersResponse getOrders(OrdersParams ordersParams) throws OKLinkException, Exception;
	
	public Order getOrder(long orderId) throws OKLinkException, Exception;
	
	/**
	 * 创建button并为之创建一个order
	 */
	public Order createOrder(Button button) throws OKLinkException, Exception;
	
	/**
	 * 创建button并为之创建一个order
	 */
	public Order createOrder(Button button, String custom, String remark) throws OKLinkException, Exception;
	
	/**
	 * 创建button并为之创建一个order，添加用户信息
	 */
	public Order createOrder(Button button, String custom, String remark, String clientName, String clientAddress, String clientEmail, String clientPhone) throws OKLinkException, Exception;
	
	public User getUser() throws OKLinkException, Exception;
	
	public UserBalance getUserBalance() throws OKLinkException, Exception;
	
	/**
	 * 获取当前用户默认钱包
	 */
	public Wallet getDefaultWallet() throws OKLinkException, Exception;
	
	/**
	 * 获取用户钱包列表 
	 */
	public List<Wallet> getWallets() throws OKLinkException, Exception;
	
	public List<Wallet> getWallets(String query) throws OKLinkException, Exception;
	
	/**
	 * 创建钱包
	 */
	public Wallet createWallet(String name) throws OKLinkException, Exception;
	
	/**
	 * 删除钱包
	 */
	public Wallet deleteWallet(long walletId) throws OKLinkException, Exception;
	
	/**
	 * 修改钱包 
	 */
	public Wallet updateWallet(long walletId, String name) throws OKLinkException, Exception;
	
	/**
	 * 设置默认钱包
	 */
	public Wallet setDefault(long walletId) throws OKLinkException, Exception;
	
	/**
	 * 获取交易详情列表
	 */
	public TransactionsResponse getTransactions() throws OKLinkException, Exception;
	
	public TransactionsResponse getTransactions(TransactionsParams transactionsParams) throws OKLinkException, Exception;
	
	/**
	 * 获取某交易信息
	 */
	public Transaction getTransaction(long transactionId) throws OKLinkException, Exception;

	/**
	 * 付款
	 */
	public Transaction sendMoney(SendParams sendParams) throws OKLinkException, Exception;

	/**
	 * 收款 
	 */
	public Transaction requestMoney(RequestParams requestParams) throws OKLinkException, Exception;
	
}
