package com.bihang.api;


import com.bihang.api.bean.User;

public class Example {
	
	private static final String KEY = "";
	private static final String SECRET = "";

	public static void main(String[] args) throws Exception {
		Bihang bihang = BihangBuilder.getInstance()
                .build(KEY, SECRET);
        User user = bihang.getUser();	
		System.out.println(user.getEmail());
	}
	
}
