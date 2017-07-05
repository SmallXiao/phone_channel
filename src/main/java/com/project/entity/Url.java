package com.project.entity;

public class Url {

	private int id;
	private int create_date;
	private String short_url;
	private String user_type;
	private String point;
	private int valid_days;
	private int account_id;
	private long create_time;
	private long update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreate_date() {
		return create_date;
	}

	public void setCreate_date(int create_date) {
		this.create_date = create_date;
	}

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public int getValid_days() {
		return valid_days;
	}

	public void setValid_days(int valid_days) {
		this.valid_days = valid_days;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}

}
