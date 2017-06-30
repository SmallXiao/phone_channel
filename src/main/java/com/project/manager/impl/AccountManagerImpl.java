package com.project.manager.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.manager.AccountManager;
import com.project.mapper.AccountMapper;

@Service("accountManager")
public class AccountManagerImpl implements AccountManager {

	private static Logger LOG = LogManager.getLogger(AccountManagerImpl.class);

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public void register(String accountName, String password) {
		long currentTime = System.currentTimeMillis() / 1000;
		accountMapper.register(accountName, password, currentTime);
	}

	@Override
	public String login(String accountName, String password) {

		return accountMapper.login(accountName, password);
	}

	@Override
	public int changePassword(String accountId, String password, String newPassword) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.changePassword(accountId, password, newPassword, currentTime);
		
	}

	@Override
	public int changePayPassword(String accountId, String oldPayPassword, String payPassword) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.changePayPassword(accountId, oldPayPassword, payPassword, currentTime);
		
	}

	@Override
	public int changePayeeName(String accountId, String payeeName) {
		long currentTime = System.currentTimeMillis() / 1000;
		return accountMapper.changePayeeName(accountId, payeeName, currentTime);
	}

	@Override
	public String getPayeeName(String accountId) {
		return accountMapper.getPayeeName(accountId);
	}

	@Override
	public int bindingBank(String accountId, String payeeName,
			String cardNumber, String bankAllas, String province, String city,
			String place) {
		// TODO Auto-generated method stub
		return 0;
	}

}
