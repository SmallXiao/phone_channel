package com.project.controller;

import java.util.List;
import java.util.Map;

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
	private final String register(HttpServletRequest request, HttpServletResponse response) {
		HttpServletUtil.initResponse(response);
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		
		LOG.trace(String.format("accountName：%s", accountName));
		accountManager.register(accountName, Md5.crypt(password));
		return HttpServletUtil.getResponseJsonData(1, "success");
	}
	
	/**
	 * 用户登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	private final String login(HttpServletRequest request, HttpServletResponse response) {
		HttpServletUtil.initResponse(response);
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		String accountId = accountManager.login(accountName, Md5.crypt(password));
		LOG.trace(String.format("accountName：%s，accountId：%s", accountName, accountId));
		
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
	private final String changePassword(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId, @RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword) {
		HttpServletUtil.initResponse(response);
		
		int result = accountManager.changePassword(accountId, Md5.crypt(password), Md5.crypt(newPassword));
		System.out.println(result);
		return HttpServletUtil.getResponseJsonData(result, accountId, "success");
	}
	
	/**
	 * 修改资金的密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/payPassword/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	private final String changePayPassword(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		LOG.trace("accountId：" + accountId);
		
		HttpServletUtil.initResponse(response);
		String oldPayPassword = request.getParameter("oldPayPassword");// 旧支付密码
		String payPassword = request.getParameter("payPassword");// 新支付密码
		
		int result = accountManager.changePayPassword(accountId, Md5.crypt(oldPayPassword), Md5.crypt(payPassword));
		
		return HttpServletUtil.getResponseJsonData(result, "success");
	}
	
	/**
	 * 修改收款人姓名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/payeeName/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	private final String changePayeeName(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String payeeName = request.getParameter("payeeName");// 收款人姓名
		LOG.trace(String.format("accountId：%s，payeeName：%s", accountId, payeeName));
		
		int result = accountManager.changePayeeName(accountId, payeeName);	
		return HttpServletUtil.getResponseJsonData(result, "success");
	}
	
	
	/**
	 * 绑定银行卡
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/bindingBank/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	private final String bindingBank(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		
		String payeeName = request.getParameter("sn");// 收款人姓名
		String cardNumber = request.getParameter("bankCardNo");// 收款人卡号
		String bankAllas = request.getParameter("bankAllas");// 开户行
		String province = request.getParameter("province");// 开户省份
		String city = request.getParameter("city");// 开户城市
		String place = request.getParameter("place");// 开户网点
		int result = accountManager.bindingBank(accountId, payeeName, cardNumber, bankAllas, province, city, place);
		
		return HttpServletUtil.getResponseJsonData(result, "success");
	}
	
	/**
	 * 获取当前登录用户绑定银行卡信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/bankards/{accountId}", method = RequestMethod.GET)
	@ResponseBody
	private final String bankards(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		
		LOG.trace("accountId：" + accountId);
		List<Map<String, Object>> bankList = accountManager.bankards(accountId);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", bankList);
		
		return jsonObject.toJSONString();
	}
	
	
	/**
	 * 得到当前登录用户的收款人姓名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/payeeName/{accountId}", method = RequestMethod.GET)
	@ResponseBody
	private final String payeeName(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String payeeName = accountManager.getPayeeName(accountId);
		String name = "";
		if (payeeName != null && "".equals(payeeName)) {
			name = payeeName.split("")[1] + "**";
		}
		
		return HttpServletUtil.getResponseJsonData(1, name, "success");
	}
	
	/**
	 * 得到当前省下市数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getcities", method = RequestMethod.GET)
	@ResponseBody
	private final String getCities(HttpServletRequest request, HttpServletResponse response) {
		HttpServletUtil.initResponse(response);
		String provinceId = request.getParameter("provinceId");
		
		List<Map<String, Object>> citiesMap = accountManager.getCities(provinceId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", citiesMap);
		
		return jsonObject.toJSONString();
	}
	
	/**
	 * 购买彩票
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/buy/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	private final String buy(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String provinceId = request.getParameter("provinceId");
		
		List<Map<String, Object>> citiesMap = accountManager.getCities(provinceId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", citiesMap);
		
		return jsonObject.toJSONString();
	}
	
	
}
