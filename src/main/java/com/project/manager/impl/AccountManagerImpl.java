package com.project.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.project.util.DateUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Link;
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

	public Link getLink(String shortUrl) {
		Link link = accountMapper.getLink(shortUrl);
		if (link != null) {// 通过比对创建日期和有效日期，判断链接是否失效
			int currentDate = DateUtils.getYYYYMMDD();
			
			int createDate = link.getCreate_date();
			int expire = link.getExpire();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar createCalendar = new GregorianCalendar();
			try {
				createCalendar.setTime(sdf.parse(String.valueOf(createDate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			createCalendar.add(Calendar.DAY_OF_MONTH, expire);
			int date = DateUtils.getYYYYMMDD(createCalendar.getTime());
			
			if (currentDate <= date) {// 当前日期在有效日期内，返回url
				return link;
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
	public List<Map<String, Object>> getCities(String provinceId) {
		return accountMapper.getCities(provinceId);
	}

	@Override
	public List<Map<String, Object>> bankards(String accountId) {
		return accountMapper.bankards(accountId);
	}

	@Override
	public List<Map<String, Object>> getUsersByParentId(String parentId) {
		return accountMapper.getUsersByParentId(parentId);
	}

	@Override
	public List<Map<String, Object>> getLinks(String accountId) {
		List<Map<String, Object>> linkList = accountMapper.getLinks(accountId);
		int currentDate = DateUtils.getYYYYMMDD();
		int createDate, expire, status;
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>(linkList.size());
		Map<String, Object> map;
		for (int i = 0, size = linkList.size(); i < size; i++) {// 通过比对创建日期和有效日期，判断链接是否失效
			map = linkList.get(i);
			status = 0;
			createDate = Integer.parseInt(map.get("create_date").toString());
			expire = Integer.parseInt(map.get("expire").toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar createCalendar = new GregorianCalendar();
			try {
				createCalendar.setTime(sdf.parse(String.valueOf(createDate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			createCalendar.add(Calendar.DAY_OF_MONTH, expire);
			int date = DateUtils.getYYYYMMDD(createCalendar.getTime());
			
			if (currentDate <= date) {// 当前日期在有效日期内，返回url
				status = 1;
			}
			map.put("status", status);
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public int deleteLink(String accountId, String linkId) {
		return accountMapper.deleteLink(accountId, linkId);
	}

}
