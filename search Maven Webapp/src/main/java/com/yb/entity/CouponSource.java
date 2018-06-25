package com.yb.entity;


public class CouponSource {
	private String days;
	private Integer city;
	private Integer coupon_type;
	private Integer score_allmoney;
	private Integer order_allmoney;
	private Integer reissued_allmoney;
	private Integer other_allmoney;
	private Integer score_usedmoney;
	private Integer order_usedmoney;
	private Integer reissued_usedmoney;
	private Integer other_usedmoney;
	public CouponSource() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CouponSource(String days, Integer city, Integer coupon_type,
			Integer score_allmoney, Integer order_allmoney,
			Integer reissued_allmoney, Integer other_allmoney,
			Integer score_usedmoney, Integer order_usedmoney,
			Integer reissued_usedmoney, Integer other_usedmoney) {
		super();
		this.days = days;
		this.city = city;
		this.coupon_type = coupon_type;
		this.score_allmoney = score_allmoney;
		this.order_allmoney = order_allmoney;
		this.reissued_allmoney = reissued_allmoney;
		this.other_allmoney = other_allmoney;
		this.score_usedmoney = score_usedmoney;
		this.order_usedmoney = order_usedmoney;
		this.reissued_usedmoney = reissued_usedmoney;
		this.other_usedmoney = other_usedmoney;
	}
	@Override
	public String toString() {
		return "CouponSource [days=" + days + ", city=" + city
				+ ", coupon_type=" + coupon_type + ", score_allmoney="
				+ score_allmoney + ", order_allmoney=" + order_allmoney
				+ ", reissued_allmoney=" + reissued_allmoney
				+ ", other_allmoney=" + other_allmoney + ", score_usedmoney="
				+ score_usedmoney + ", order_usedmoney=" + order_usedmoney
				+ ", reissued_usedmoney=" + reissued_usedmoney
				+ ", other_usedmoney=" + other_usedmoney + "]";
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
	public Integer getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(Integer coupon_type) {
		this.coupon_type = coupon_type;
	}
	public Integer getScore_allmoney() {
		return score_allmoney;
	}
	public void setScore_allmoney(Integer score_allmoney) {
		this.score_allmoney = score_allmoney;
	}
	public Integer getOrder_allmoney() {
		return order_allmoney;
	}
	public void setOrder_allmoney(Integer order_allmoney) {
		this.order_allmoney = order_allmoney;
	}
	public Integer getReissued_allmoney() {
		return reissued_allmoney;
	}
	public void setReissued_allmoney(Integer reissued_allmoney) {
		this.reissued_allmoney = reissued_allmoney;
	}
	public Integer getOther_allmoney() {
		return other_allmoney;
	}
	public void setOther_allmoney(Integer other_allmoney) {
		this.other_allmoney = other_allmoney;
	}
	public Integer getScore_usedmoney() {
		return score_usedmoney;
	}
	public void setScore_usedmoney(Integer score_usedmoney) {
		this.score_usedmoney = score_usedmoney;
	}
	public Integer getOrder_usedmoney() {
		return order_usedmoney;
	}
	public void setOrder_usedmoney(Integer order_usedmoney) {
		this.order_usedmoney = order_usedmoney;
	}
	public Integer getReissued_usedmoney() {
		return reissued_usedmoney;
	}
	public void setReissued_usedmoney(Integer reissued_usedmoney) {
		this.reissued_usedmoney = reissued_usedmoney;
	}
	public Integer getOther_usedmoney() {
		return other_usedmoney;
	}
	public void setOther_usedmoney(Integer other_usedmoney) {
		this.other_usedmoney = other_usedmoney;
	}
	
	
}
