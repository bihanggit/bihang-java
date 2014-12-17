
#OKLink Java Client Library
This is the java client library for OKLink API,An easy way to send,request,accept bitcoin.

#Dependencies
> * Apache httpclient
> * jackson
> * commons-io

#Usage
##APIKey to access your own accounts
your should create an APIKey on [OKLink](https://www.oklink.com/apiKey/index.do) and enabling it.
```java
OKLink oklink = OKLinkBuilder.getInstance()
				.build("api_key", "api_secret");
```

##OAuth 2.0 to access others accounts
your should craete an application on [OKLink](https://www.oklink.com/oauth/application.do).
```java
OKLink oklink = OKLinkBuilder.getInstance()
				.build("access_token");
```
#Examples
##Get user info
```java
User user = oklink.getUser();
```

##Get user balance
```java
UserBalance user = oklink.getUserBalance();
```

##Request
```java
RequestParams params = new RequestParams();
params.setFromPhone("your_phone_num", "area_code");
params.setAmount(new Amount(0.001, CoinType.BTC));
Transaction transaction = oklink.requestMoney(params);
```
if the target is the phone number,you need to add the area code.You can pass `/api/v1/nations` get the country code
##Create button
```java
Button button = new Button();
button.setName("button_name")
	  .setPrice(new Amount(0.001, CoinType.BTC))
	  .setCustom("custom_code");
button = oklink.createButton(button);
```







