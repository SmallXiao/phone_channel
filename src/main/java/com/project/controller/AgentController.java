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
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.entity.Url;
import com.project.manager.AccountManager;
import com.project.util.CommonUtil;
import com.project.util.HttpServletUtil;
import com.project.util.Md5;

/**
 * 代理商
 *
 */
@Controller
@RequestMapping("/agent")
public class AgentController {

	private static final Logger LOG = LogManager.getLogger(AgentController.class);

	@Autowired
	private AccountManager accountManager;

	/**
	 * 代理创建帐号
	 */
	@RequestMapping(value="/create/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	private final String create(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") int accountId) {
		HttpServletUtil.initResponse(response);
		String userType = request.getParameter("userType");// 用户类型
		String name = request.getParameter("username");// 用户名
		String password = request.getParameter("password");// 密码
		String point = request.getParameter("point");// 返点

		int result = accountManager.createAgent(name, Md5.crypt(password), point, userType, accountId);

		return HttpServletUtil.getResponseJsonData(result, name, "success");
	}

	/**
	 * 通过短链接开户
	 */
	@RequestMapping(value="/create_link/{accountId}", method=RequestMethod.POST)
	@ResponseBody
	private final String createLink(HttpServletRequest request, HttpServletResponse response,
						 @PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String userType = request.getParameter("type");// 用户类型（普通会员、代理）
		String point = request.getParameter("point");// 返点
		String validDate = request.getParameter("validDays");// 有效期
		String longUrl = String.format("register.html?userType=%s&point=%s", userType, point);// 长链接
		String shortUrl = "agent/register/" + CommonUtil.shortUrl(longUrl);// 短链接

		int result = accountManager.createLink(shortUrl, longUrl, userType, point, validDate, accountId);

		return HttpServletUtil.getResponseJsonData(result, "success");
	}

	/**
	 * 通过短链接匹配得到长连接
	 */
	@RequestMapping(value="/{shortUrl}", method=RequestMethod.GET)
	private final String redirectUrl(HttpServletResponse response, @PathVariable("shortUrl") String shortUrl) {
		HttpServletUtil.initResponse(response);

		Url url = accountManager.getUrl(shortUrl);// 得到短链接的长链接地址
		if (url != null) {
			return "forward:/register.html";
		}
		return "redirect:/404.html";
	}


	/**
	 * 通过短链接请求用户注册
	 */
	@RequestMapping(value="/register/{shortUrl}", method=RequestMethod.POST)
	@ResponseBody
	private final String register(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("shortUrl") String shortUrl) {
		HttpServletUtil.initResponse(response);
		String name = request.getParameter("userName");// 用户名
		String password = request.getParameter("password");// 用户密码

		LOG.trace(String.format("accountName：%s", name));
		
		Url url = accountManager.getUrl(shortUrl);
		int result = 0;
		if (url != null) {// 存在短链接
			result = accountManager.createAgent(name, Md5.crypt(password), url.getPoint(), url.getUser_type(), url.getAccount_id());
		}
		return HttpServletUtil.getResponseJsonData(result, "success");
	}


	/**
	 * 修改代理上下用户信息
	 */
	@RequestMapping(value="/change/{accountId}", method=RequestMethod.POST)
	@ResponseBody
	private final String change(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String payeeName = accountManager.getPayeeName(accountId);
		String name = "";
		if (payeeName != null && "".equals(payeeName)) {
			name = payeeName.split("")[1] + "**";
		}
		
		return HttpServletUtil.getResponseJsonData(1, name, "success");
	}
}
