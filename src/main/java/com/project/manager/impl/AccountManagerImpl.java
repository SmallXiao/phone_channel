package com.project.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.project.util.DateUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Url;
import com.project.manager.AccountManager;
import com.project.mapper.AccountMapper;

@Service("accountManager")
public class AccountManagerImpl implements AccountManager {

	private static Logger LOG = LogManager.getLogger(AccountManagerImpl.class);

	@Autowired
	private AccountMapper accountMapper;

	public void register(String accountName, String password) {
		long currentTime = System.currentTimeMillis() / 1000;
		accountMapper.register(accountName, password, currentTime);
	}

	public String login(String accountName, String password) {

		return accountMapper.login(accountName, password);
	}

	public int changePassword(String accountId, String password, String newPassword) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.changePassword(accountId, password, newPassword, currentTime);
		
	}

	public int changePayPassword(String accountId, String oldPayPassword, String payPassword) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.changePayPassword(accountId, oldPayPassword, payPassword, currentTime);
		
	}

	public int changePayeeName(String accountId, String payeeName) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.changePayeeName(accountId, payeeName, currentTime);
	}

	public String getPayeeName(String accountId) {
		return accountMapper.getPayeeName(accountId);
	}

	public int bindingBank(String accountId, String payeeName,
			String cardNumber, String bankAllas, String province, String city,
			String place) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.bindingBank(accountId, payeeName, cardNumber, bankAllas, province, city, place, currentTime);
	}

	public int createAgent(String name, String password, String point, String userType, int parentId) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.createAgent(name, password, point, userType, parentId, currentTime);
	}

	public Url getUrl(String shortUrl) {
		Url url = accountMapper.getUrl(shortUrl);
		if (url != null) {
			int currentDate = DateUtils.getYYYYMMDD();
			
			int createDate = url.getCreate_date();
			int validDays = url.getValid_days();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar createCalendar = new GregorianCalendar();
			try {
				createCalendar.setTime(sdf.parse(String.valueOf(createDate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			createCalendar.add(Calendar.DAY_OF_MONTH, validDays);
			int date = DateUtils.getYYYYMMDD(createCalendar.getTime());
			
			if (currentDate <= date) {// 当前日期在有效日期内，返回url
				return url;
			}
		}
		return null;
	}

	public int createLink(String shortUrl, String userType, String point,
						  String validDays, int accountId) {
		long currentTime = System.currentTimeMillis() / 1000;
		int createDate = DateUtils.getYYYYMMDD();
		return accountMapper.createLink(shortUrl, userType, point, createDate, validDays, accountId, currentTime);
	}

	@Override
	public List<Map<String, String>> getCities(String provinceId) {
		return accountMapper.getCities(provinceId);
	}

}
