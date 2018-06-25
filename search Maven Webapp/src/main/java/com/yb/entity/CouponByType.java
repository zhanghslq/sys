package com.yb.entity;

public class CouponByType {
	private String days;
	private Integer discount_allmoney;
	private Integer reduction_allmoney;
	private Integer giving_allmoney;
	private Integer discount_usedmoney;
	private Integer reduction_usedmoney;
	private Integer giving_usedmoney;
	public CouponByType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "CouponByType [days=" + days + ", discount_allmoney="
				+ discount_allmoney + ", reduction_allmoney="
				+ reduction_allmoney + ", giving_allmoney=" + giving_allmoney
				+ ", discount_usedmoney=" + discount_usedmoney
				+ ", reduction_usedmoney=" + reduction_usedmoney
				+ ", giving_usedmoney=" + giving_usedmoney + "]";
	}

	public CouponByType(String days, Integer discount_allmoney,
			Integer reduction_allmoney, Integer giving_allmoney,
			Integer discount_usedmoney, Integer reduction_usedmoney,
			Integer giving_usedmoney) {
		super();
		this.days = days;
		this.discount_allmoney = discount_allmoney;
		this.reduction_allmoney = reduction_allmoney;
		this.giving_allmoney = giving_allmoney;
		this.discount_usedmoney = discount_usedmoney;
		this.reduction_usedmoney = reduction_usedmoney;
		this.giving_usedmoney = giving_usedmoney;
	}
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public Integer getDiscount_allmoney() {
		return discount_allmoney;
	}
	public void setDiscount_allmoney(Integer discount_allmoney) {
		this.discount_allmoney = discount_allmoney;
	}
	public Integer getReduction_allmoney() {
		return reduction_allmoney;
	}
	public void setReduction_allmoney(Integer reduction_allmoney) {
		this.reduction_allmoney = reduction_allmoney;
	}
	public Integer getGiving_allmoney() {
		return giving_allmoney;
	}
	public void setGiving_allmoney(Integer giving_allmoney) {
		this.giving_allmoney = giving_allmoney;
	}
	public Integer getDiscount_usedmoney() {
		return discount_usedmoney;
	}
	public void setDiscount_usedmoney(Integer discount_usedmoney) {
		this.discount_usedmoney = discount_usedmoney;
	}
	public Integer getReduction_usedmoney() {
		return reduction_usedmoney;
	}
	public void setReduction_usedmoney(Integer reduction_usedmoney) {
		this.reduction_usedmoney = reduction_usedmoney;
	}
	public Integer getGiving_usedmoney() {
		return giving_usedmoney;
	}
	public void setGiving_usedmoney(Integer giving_usedmoney) {
		this.giving_usedmoney = giving_usedmoney;
	}
	
}
