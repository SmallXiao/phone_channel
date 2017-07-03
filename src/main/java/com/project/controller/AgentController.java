package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.manager.AccountManager;
import com.project.util.HttpServletUtil;
import com.project.util.Md5;

/**
 * 代理商
 */
@Controller
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AccountManager accountManager;

	/**
	 * 代理创建帐号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/create/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String userType = request.getParameter("userType");// 用户类型
		String name = request.getParameter("username");// 用户名
		String password = request.getParameter("password");// 密码
		String point = request.getParameter("point");// 返点

		int result = accountManager.createAgent(name, Md5.crypt(password), point, userType, accountId);
		
		return HttpServletUtil.getResponseJsonData(result, name, "success");
	}
	
	/**
	 * 修改代理上下用户信息
	 * @param request
	 * @param response
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="/change/{accountId}", method=RequestMethod.POST)
	public String change(HttpServletRequest request, HttpServletResponse response,
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
