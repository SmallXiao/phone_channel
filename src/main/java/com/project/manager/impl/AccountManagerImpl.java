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

}
