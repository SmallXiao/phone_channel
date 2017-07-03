package com.project.manager;

public interface AccountManager {

	public void register(String accountName, String password);
	
	public String login(String accountName, String password);
	
	public int changePassword(String accountId, String password, String newPassword);
	
	public int changePayPassword(String accountId, String password, String payPassword);
	
	public int changePayeeName(String accountId, String payeeName);
	
	public String getPayeeName(String accountId);
	
	public int bindingBank(String accountId, String payeeName, String cardNumber, String bankAllas,
			String province, String city, String place);
	
	
	public int createAgent(String name, String password, String point, String userType, String parentId);
}
