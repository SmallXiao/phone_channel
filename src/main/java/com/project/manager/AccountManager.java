package com.project.manager;

import java.util.List;
import java.util.Map;

import com.project.entity.Link;

public interface AccountManager {

	/**
	 * 用户注册
	 * @param accountName
	 * @param password
	 */
	public void register(String accountName, String password);
	
	public String login(String accountName, String password);
	
	public int changePassword(String accountId, String password, String newPassword);
	
	public int changePayPassword(String accountId, String password, String payPassword);
	
	public int changePayeeName(String accountId, String payeeName);
	
	public String getPayeeName(String accountId);

	/**
	 * 绑定银行卡
	 * @param accountId
	 * @param payeeName
	 * @param cardNumber
	 * @param bankAllas
	 * @param province
	 * @param city
	 * @param place
	 * @return
	 */
	public int bindingBank(String accountId, String payeeName, String cardNumber, String bankAllas,
			String province, String city, String place);

	/**
	 * 创建代理、会员用户
	 * @param name
	 * @param password
	 * @param point
	 * @param userType
	 * @param parentId
	 * @return
	 */
	public int createAgent(String name, String password, String point, String userType, int parentId);


	/**
	 * 通过短链接得到长链接
	 * @param shortUrl		短链接
	 * @return
	 */
	public Link getLink(String shortUrl);
	
	public int createLink(String shortUrl, String userType, String point, String validDays,
						  int accountId);
	
	/**
	 * 得到当前省下市数据
	 * @param provinceId
	 * @return
	 */
	public List<Map<String, Object>> getCities(String provinceId);
	
	public List<Map<String, Object>> bankards(String accountId);
	
	public List<Map<String, Object>> getUsersByParentId(String parentId);
	
	public List<Map<String, Object>> getLinks(String accountId);

	public int deleteLink(String accountId, String linkId);
}
