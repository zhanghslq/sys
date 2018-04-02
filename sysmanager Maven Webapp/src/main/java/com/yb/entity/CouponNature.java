package com.yb.entity;

import java.util.Date;

public class CouponNature {
	private Date days;
	private Integer city;
	private String tactics_type;
	private Integer coupon_type;
	private Integer off_allmoney;
	private Integer discount_allmoney;
	private Integer off_usedmoney;
	private Integer discount_usedmoney;
	public CouponNature() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CouponNature(Date days, Integer city, String tactics_type,
			Integer coupon_type, Integer off_allmoney,
			Integer discount_allmoney, Integer off_usedmoney,
			Integer discount_usedmoney) {
		super();
		this.days = days;
		this.city = city;
		this.tactics_type = tactics_type;
		this.coupon_type = coupon_type;
		this.off_allmoney = off_allmoney;
		this.discount_allmoney = discount_allmoney;
		this.off_usedmoney = off_usedmoney;
		this.discount_usedmoney = discount_usedmoney;
	}
	@Override
	public String toString() {
		return "CouponNature [days=" + days + ", city=" + city
				+ ", tactics_type=" + tactics_type + ", coupon_type="
				+ coupon_type + ", off_allmoney=" + off_allmoney
				+ ", discount_allmoney=" + discount_allmoney
				+ ", off_usedmoney=" + off_usedmoney + ", discount_usedmoney="
				+ discount_usedmoney + "]";
	}
	public Date getDays() {
		return days;
	}
	public void setDays(Date days) {
		this.days = days;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getTactics_type() {
		return tactics_type;
	}
	public void setTactics_type(String tactics_type) {
		this.tactics_type = tactics_type;
	}
	public Integer getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(Integer coupon_type) {
		this.coupon_type = coupon_type;
	}
	public Integer getOff_allmoney() {
		return off_allmoney;
	}
	public void setOff_allmoney(Integer off_allmoney) {
		this.off_allmoney = off_allmoney;
	}
	public Integer getDiscount_allmoney() {
		return discount_allmoney;
	}
	public void setDiscount_allmoney(Integer discount_allmoney) {
		this.discount_allmoney = discount_allmoney;
	}
	public Integer getOff_usedmoney() {
		return off_usedmoney;
	}
	public void setOff_usedmoney(Integer off_usedmoney) {
		this.off_usedmoney = off_usedmoney;
	}
	public Integer getDiscount_usedmoney() {
		return discount_usedmoney;
	}
	public void setDiscount_usedmoney(Integer discount_usedmoney) {
		this.discount_usedmoney = discount_usedmoney;
	}
	
}
