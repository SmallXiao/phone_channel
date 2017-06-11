package com.project.manager.impl;

import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.project.manager.HuafeiduoManager;
import com.project.util.HttpUtil;
import com.project.util.Md5;

@Service("huafeiduoManager")
public class HuafeiduoManagerImpl implements HuafeiduoManager{
	
	private static final Logger LOG = LogManager.getLogger(HuafeiduoManagerImpl.class);

	private static final String API_KEY = "oDd92EdyQwBFisvfdSdwNvdVrwLVSpK7SN5Jh89XsysBRNHUvKg97jTnGu9nJHjr";
	private static final String SECRET_KEY = "kc2s0YesmxT4apNv1UuV4c9zx7M7DVnOYd7X30edxY8xcKS3tbNmeLLBHnx09LNW";
	// 查询账户余额
	private static String ACCOUNT_BALANCE = "http://api.huafeiduo.com/gateway.cgi?mod=account.balance&api_key=%s&sign=%s";
	
	// 检查手机号是否能下单充值，并获取下单(进货)价格，以及手机号运营商、归属地等相关信息
	private static String ORDER_PHONE_CHECK = "http://api.huafeiduo.com/gateway.cgi?mod=order.phone.check&phone_number=%s&card_worth=%s&api_key=%s&sign=%s";
	
	// 提交充值订单
	private static String ORDER_PHONE_SUBMIT = "http://api.huafeiduo.com/gateway.cgi?mod=order.phone.submit&phone_number=%s&card_worth=%s&sp_order_id=%s&api_key=%s&sign=%s";
	
	// 查询充值订单状态
	private static String ORDER_PHONE_STATUS = "http://api.huafeiduo.com/gateway.cgi?mod=order.phone.status&sp_order_id=%s&api_key=%s&sign=%s";
	
	private static String NOTIFY_URL = "notify_url";
	
	public void getAccountBalance() {
		String sign = Md5.crypt("api_key" + API_KEY + SECRET_KEY);
		String url = String.format(ACCOUNT_BALANCE, API_KEY, sign);
		System.out.println(url);
		String result = HttpUtil.get(url);
		LOG.info(result);
	}

	public void orderPhoneCheck(String phoneNumber, String cardWorth) {
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("phone_number", phoneNumber);
		treeMap.put("card_worth", cardWorth);
		treeMap.put("api_key", API_KEY);
		StringBuilder signBuilder = new StringBuilder(60);
		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			signBuilder.append(entry.getKey()).append(entry.getValue());
		}
		signBuilder.append(SECRET_KEY);
		String sign = Md5.crypt(signBuilder.toString());
		System.out.println(sign);
		String url = String.format(ORDER_PHONE_CHECK, phoneNumber, cardWorth, API_KEY, sign);
		String result = HttpUtil.get(url);
		LOG.info(result);
	}

	public void orderPhoneSubmit(String phoneNumber, String cardWorth,
			String spOrderId) {
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("phone_number", phoneNumber);
		treeMap.put("card_worth", cardWorth);
		treeMap.put("api_key", API_KEY);
		treeMap.put("sp_order_id", spOrderId);
		StringBuilder signBuilder = new StringBuilder(100);
		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			signBuilder.append(entry.getKey()).append(entry.getValue());
		}
		signBuilder.append(SECRET_KEY);
		String sign = Md5.crypt(signBuilder.toString());
		
		String result = HttpUtil.get(String.format(ORDER_PHONE_SUBMIT, phoneNumber, cardWorth, spOrderId, API_KEY, sign));
		LOG.info(result);
	}

	public void orderPhoneStatus(String spOrderId) {
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("api_key", API_KEY);
		treeMap.put("sp_order_id", spOrderId);
		StringBuilder signBuilder = new StringBuilder(20);
		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			signBuilder.append(entry.getKey()).append(entry.getValue());
		}
		signBuilder.append(SECRET_KEY);
		String sign = Md5.crypt(signBuilder.toString());
		
		String result = HttpUtil.get(String.format(ORDER_PHONE_STATUS, spOrderId, API_KEY, sign.toString()));
		LOG.info(result);
	}
	


}
