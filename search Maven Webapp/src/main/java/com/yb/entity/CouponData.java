package com.yb.entity;

import java.util.Date;

public class CouponData {
	private Integer ID;
	private String coupon_title;
	private String tactics_title;
	private Date send_time;
	private Date used_time;
	private String tactics_type;
	private String coupon_type;
	private Integer discount_amount;
	private Integer station_id;
	private String coupon_status;
	public CouponData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CouponData(Integer iD, String coupon_title, String tactics_title,
			Date send_time, Date used_time, String tactics_type,
			String coupon_type, Integer discount_amount, Integer station_id,
			String coupon_status) {
		super();
		ID = iD;
		this.coupon_title = coupon_title;
		this.tactics_title = tactics_title;
		this.send_time = send_time;
		this.used_time = used_time;
		this.tactics_type = tactics_type;
		this.coupon_type = coupon_type;
		this.discount_amount = discount_amount;
		this.station_id = station_id;
		this.coupon_status = coupon_status;
	}
	@Override
	public String toString() {
		return "CouponData [ID=" + ID + ", coupon_title=" + coupon_title
				+ ", tactics_title=" + tactics_title + ", send_time="
				+ send_time + ", used_time=" + used_time + ", tactics_type="
				+ tactics_type + ", coupon_type=" + coupon_type
				+ ", discount_amount=" + discount_amount + ", station_id="
				+ station_id + ", coupon_status=" + coupon_status + "]";
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getCoupon_title() {
		return coupon_title;
	}
	public void setCoupon_title(String coupon_title) {
		this.coupon_title = coupon_title;
	}
	public String getTactics_title() {
		return tactics_title;
	}
	public void setTactics_title(String tactics_title) {
		this.tactics_title = tactics_title;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public Date getUsed_time() {
		return used_time;
	}
	public void setUsed_time(Date used_time) {
		this.used_time = used_time;
	}
	public String getTactics_type() {
		return tactics_type;
	}
	public void setTactics_type(String tactics_type) {
		this.tactics_type = tactics_type;
	}
	public String getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
	}
	public Integer getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(Integer discount_amount) {
		this.discount_amount = discount_amount;
	}
	public Integer getStation_id() {
		return station_id;
	}
	public void setStation_id(Integer station_id) {
		this.station_id = station_id;
	}
	public String getCoupon_status() {
		return coupon_status;
	}
	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}
	
	
}
