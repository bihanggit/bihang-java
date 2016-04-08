package com.bihang.api;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Example {
	
	private static final String KEY = "YOUR_KEY";
	private static final String SECRET = "YOUR_SECRET";
	private static final String BASE_URL = "https://www.bihang.com";

	public static String doPOST(String url, Object requestBody) throws Exception {

		HttpRequestBase request;
		ObjectMapper mapper = new ObjectMapper();

		HttpPost post = new HttpPost(BASE_URL + url);
		// 设置POST的请求数据
		if (requestBody != null) {
			post.setEntity(new StringEntity(mapper.writeValueAsString(requestBody), "utf-8"));
		}
		request = post;

		String nonce = String.valueOf(System.currentTimeMillis());
		String message = nonce + (BASE_URL + url) + (requestBody == null ? "" : mapper.writeValueAsString(requestBody));
		
		// 请求信息加密
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(SECRET.getBytes(), "HmacSHA256"));
		String signature = new String(Hex.encodeHex(mac.doFinal(message.getBytes())));
		
		// 设置API认证头信息
		request.setHeader("ACCESS_KEY", KEY);
		request.setHeader("ACCESS_SIGNATURE", signature);
		request.setHeader("ACCESS_NONCE", nonce);

		HttpClient httpClient = HttpClientBuilder.create().build();
		// 执行请求
		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();
		InputStream is = null;
		try {
			if (entity != null) {
				is = entity.getContent();
				return IOUtils.toString(is, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "default2");
		
		System.out.println(doPOST("/api/v1/wallets", params));
	}
	
}
