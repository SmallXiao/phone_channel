package com.project.mapper;

import org.apache.ibatis.annotations.Param;


public interface AccountMapper {
	
	public void register(@Param("name") String accountName, @Param("password") String password, @Param("time") long time);
	/**
	 * 用户登陆
	 * @param accountName
	 * @param password
	 */
	public String login(@Param("name") String accountName, @Param("password")String password);
	
	public int changePassword(@Param("id") String accountId, @Param("password") String password, 
			@Param("newPassword") String newPassword, @Param("time") long time);
	
	public int changePayPassword(@Param("id") String accountId, @Param("old_pay_password") String oldPayPassword, 
			@Param("pay_password") String payPassword, @Param("time") long time);
	
	public int changePayeeName(@Param("id") String accountId, @Param("pay_password") String payPassword, 
			@Param("payee_name") String payeeName, @Param("time") long time); 
}
