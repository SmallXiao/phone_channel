package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/pay")
public class PayController {

	private static final Logger LOG = LogManager.getLogger(PayController.class);
	
	@RequestMapping(value="/recharge", method=RequestMethod.GET)
	@ResponseBody
	public void recharge(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
}
