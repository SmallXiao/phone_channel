package com.project.manager.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.manager.QianmiManager;
import com.project.util.HttpUtil;
import com.project.util.Md5;


public class QianmiManagerImpl implements QianmiManager{

    private static final Logger LOG = LogManager.getLogger(QianmiManagerImpl.class);
//    private static final String PARTNER = "S018968";
    private static final String PARTNER = "S072596";
    private static final String TPLID = "MB2017022615238518";
//    private static final String API_KEY = "w5p1dw2b6lwww2fsvmf8p6ynr78h2ltoxkg75ksmu6";
    private static final String API_KEY = "a2ejcjbqlh1jpzh7w4lqm9scesejporoycoixe3voc";
    
    public void supply(String reqId) {
        String supply_url = "http://supply.api.17sup.com/supply.do?partner=%s&tplid=%s&sign=%s&reqid=%s&format=json";
//    	String supply_url = "http://api2.ofpay.com/supply.do?partner=%s&tplid=%s&sign=%s&reqid=%s&format=json";
        String sign = Md5.crypt(PARTNER + TPLID + API_KEY).toUpperCase();
        String result = HttpUtil.get(String.format(supply_url, PARTNER, TPLID, sign, reqId));
        LOG.info(result);
    }

    public void checkOrder(String orderIds, String reqId) {
        String check_order_url = "http://supply.api.17sup.com/checkOrder.do?partner=%s&tplid=%s&sign=%s&orderids=%s&reqid=%s&format=json";
        String sign = Md5.crypt(PARTNER + TPLID + reqId + API_KEY).toUpperCase();
        String result = HttpUtil.get(String.format(check_order_url, PARTNER, TPLID, sign, orderIds, reqId));
        LOG.info(result);

    }

    public void confirmRecharge(String orderId, String id) {
        String confirm_recharge_url = "http://supply.api.17sup.com/confirmRecharge.do?orderid=%s&id=%s&format=json";
        String result = HttpUtil.get(String.format(confirm_recharge_url, orderId, id));
        LOG.info(result);
    }

    public void setOrder(String orderId, String id, String orderState) {
//        String orderState = "4";
        String set_order_url = "http://supply.api.17sup.com/setOrders.do?orderid=%s&id=%s&sign=%s&orderstate=%s&partner=%s&tplid=%s&version=2.4&format=json";
        String sign = Md5.crypt(PARTNER + orderId + orderState + API_KEY).toUpperCase();
        String result = HttpUtil.get(String.format(set_order_url, orderId, id, sign, orderState, PARTNER, TPLID));
        LOG.info(result);

    }
    
    public static void main(String[] args) {
		new QianmiManagerImpl().supply("1234");
    	String orderId = "1234";
    	String id = "";
    	String orderIds = "1234";
    	String reqId=  "1234";
    	
//    	new QianmiManagerImpl().checkOrder(orderIds, reqId);
    	
//    	new QianmiManagerImpl().confirmRecharge(orderId, id);
    }
}
	