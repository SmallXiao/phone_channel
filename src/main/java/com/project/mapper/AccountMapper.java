package com.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.project.entity.Url;

public interface AccountMapper {

	public void register(@Param("name") String accountName, @Param("password") String password,
			@Param("time") long time);

	/**
	 * 用户登陆
	 * 
	 * @param accountName
	 * @param password
	 */
	public String login(@Param("name") String accountName, @Param("password") String password);

	public int changePassword(@Param("id") String accountId, @Param("password") String password,
			@Param("newPassword") String newPassword, @Param("time") long time);

	public int changePayPassword(@Param("id") String accountId, @Param("old_pay_password") String oldPayPassword,
			@Param("pay_password") String payPassword, @Param("time") long time);

	public int changePayeeName(@Param("id") String accountId, @Param("payee_name") String payeeName,
			@Param("time") long time);

	public String getPayeeName(@Param("id") String accountId);

	public int createAgent(@Param("name") String name, @Param("password") String password, @Param("point") String point,
			@Param("type") String userType, @Param("parentId") int parentId, @Param("time") long time);

	public Url getUrl(@Param("shortUrl") String shortUrl);

	public int createLink(@Param("shortUrl") String shortUrl, @Param("userType") String userType,
			@Param("point") String point, @Param("createDate") int createDate, @Param("validDays") String validDays,
			@Param("accountId") int accountId, @Param("time") long time);
	
	public List<Map<String, String>> getCities(@Param("provinceId")String provinceId);
	
	public int bindingBank(@Param("accountId") String accountId, @Param("payeeName")String payeeName, @Param("cardNumber")String cardNumber,
			@Param("bankAllas")String bankAllas, @Param("province")String province, @Param("city")String city, @Param("place")String place, @Param("time")long time);

}
