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
}
