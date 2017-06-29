package com.project.manager;

public interface AccountManager {

	public void register(String accountName, String password);
	
	public String login(String accountName, String password);
	
	public int changePassword(String accountId, String password, String newPassword);
	
	public int changePayPassword(String accountId, String password, String payPassword);
	
	public int changePayeeName(String accountId, String payeeName, String payPassword);
}
