package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.manager.AccountManager;
import com.project.util.HttpServletUtil;
import com.project.util.Md5;

/**
 * 用户管理
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {

	private static Logger LOG = LogManager.getLogger(AccountController.class);
	@Autowired
	private AccountManager accountManager;
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response) {
		HttpServletUtil.initResponse(response);
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		
		accountManager.register(accountName, Md5.crypt(password));
//		return HttpServletUtil.getResponseJsonData(1, "success");
		return null;
	}
	
	
	/**
	 * 用户登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		HttpServletUtil.initResponse(response);
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		String accountId = accountManager.login(accountName, Md5.crypt(password));
		LOG.trace(String.format("accountName：%s，password：%s，count：%s", accountName, password, accountId));
		
		
		return HttpServletUtil.getResponseJsonData(1, accountId, "success");
	}
	
	/**
	 * 修改登陆密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/password/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId, @RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword) {
		HttpServletUtil.initResponse(response);
		
		int result = accountManager.changePassword(accountId, Md5.crypt(password), Md5.crypt(newPassword));
		System.out.println(result);
		return null;
	}
	
	/**
	 * 修改资金的密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/payPassword/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	public String changePayPassword(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String password = request.getParameter("password");// 旧密码
		String newPassword = request.getParameter("newPassword");// 新密码
		
		return null;
	}
	
	/**
	 * 修改收款人姓名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/payeeName", method = RequestMethod.POST)
	@ResponseBody
	public String changePayeeName(HttpServletRequest request, HttpServletResponse response) {
		HttpServletUtil.initResponse(response);
		String accountName = request.getParameter("accountName");// 用户名
		String oldPassword = request.getParameter("oldPass");// 旧密码
		String newPassword = request.getParameter("newPass");// 新密码
		
		return null;
	}
	
	
	/**
	 * 绑定银行卡
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/bindingBack", method = RequestMethod.POST)
	public String bindingBank(HttpServletRequest request, HttpServletResponse response) {
		HttpServletUtil.initResponse(response);
		
		
		return null;
		
		
	}
}
