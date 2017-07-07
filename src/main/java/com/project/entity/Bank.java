package com.project.entity;

public class Bank {

	private int id;
	private String payee_name;// 收款人姓名
	private String card_number;// 银行卡号
	private String bank_allas;// 开户行
	private String province;// 开户省份
	private String city;// 开户城市
	private String place;// 开户网点
	private int create_time;
	private int update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPayee_name() {
		return payee_name;
	}

	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getBank_allas() {
		return bank_allas;
	}

	public void setBank_allas(String bank_allas) {
		this.bank_allas = bank_allas;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getCreate_time() {
		return create_time;
	}

	public void setCreate_time(int create_time) {
		this.create_time = create_time;
	}

	public int getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(int update_time) {
		this.update_time = update_time;
	}

}
