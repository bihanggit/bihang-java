package com.bihang.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bihang.api.bean.Amount;
import com.bihang.api.bean.CoinType;
import com.bihang.api.bean.SendParams;

/**
 * @author gengchj@foxmail.com
 * @version 2014-11-7 上午11:31:00
 */
public class AppTest {

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SendParams params = new SendParams();
		params.setToEmail("gengchj@foxmail.com");
		params.setAmount(new Amount(0.001, CoinType.LTC));
		params.setOffchain(false);
		System.out.println(mapper.writeValueAsString(params));
		
	}
	
}
