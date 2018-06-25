package com.yb.entity;


public class CouponOil {
	private String days;
	private Integer city;
	private String tactics_type;
	private Integer coupon_type;
	private Integer oil_allmoney;
	private Integer notoil_allmoney;
	private Integer oil_usedmoney;
	private Integer notoil_usedmoney;
	public CouponOil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CouponOil(String days, Integer city, String tactics_type,
			Integer coupon_type, Integer oil_allmoney, Integer notoil_allmoney,
			Integer oil_usedmoney, Integer notoil_usedmoney) {
		super();
		this.days = days;
		this.city = city;
		this.tactics_type = tactics_type;
		this.coupon_type = coupon_type;
		this.oil_allmoney = oil_allmoney;
		this.notoil_allmoney = notoil_allmoney;
		this.oil_usedmoney = oil_usedmoney;
		this.notoil_usedmoney = notoil_usedmoney;
	}
	@Override
	public String toString() {
		return "CouponOil [days=" + days + ", city=" + city + ", tactics_type="
				+ tactics_type + ", coupon_type=" + coupon_type
				+ ", oil_allmoney=" + oil_allmoney + ", notoil_allmoney="
				+ notoil_allmoney + ", oil_usedmoney=" + oil_usedmoney
				+ ", notoil_usedmoney=" + notoil_usedmoney + "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
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
	public Integer getOil_allmoney() {
		return oil_allmoney;
	}
	public void setOil_allmoney(Integer oil_allmoney) {
		this.oil_allmoney = oil_allmoney;
	}
	public Integer getNotoil_allmoney() {
		return notoil_allmoney;
	}
	public void setNotoil_allmoney(Integer notoil_allmoney) {
		this.notoil_allmoney = notoil_allmoney;
	}
	public Integer getOil_usedmoney() {
		return oil_usedmoney;
	}
	public void setOil_usedmoney(Integer oil_usedmoney) {
		this.oil_usedmoney = oil_usedmoney;
	}
	public Integer getNotoil_usedmoney() {
		return notoil_usedmoney;
	}
	public void setNotoil_usedmoney(Integer notoil_usedmoney) {
		this.notoil_usedmoney = notoil_usedmoney;
	}
	
	
	
}
