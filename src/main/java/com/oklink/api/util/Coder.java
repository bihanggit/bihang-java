package com.oklink.api.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * 加密类
 */
public class Coder {

	/**
	 * byte[]:string  byte-->2*string 8位转成[0-9a-z]
	 * byte:32 --> string:64
	 * @param secret
	 * @param message
	 * @return
	 */
	public static String encryptHMAC(String secret, String message) {
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
			return new String(Hex.encodeHex(mac.doFinal(message.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
