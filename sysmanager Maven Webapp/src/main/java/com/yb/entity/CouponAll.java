package com.yb.entity;

public class CouponAll {
	private String days;
	private Integer oil_allmoney;
	private Integer notoil_allmoney;
	private Integer score_allmoney;
	private Integer oil_usedmoney;
	private Integer notoil_usedmoney;
	private Integer score_usedmoney;
	private Integer order_allmoney;
	private Integer order_usedmoney;
	public CouponAll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CouponAll(String days, Integer oil_allmoney,
			Integer notoil_allmoney, Integer score_allmoney,
			Integer oil_usedmoney, Integer notoil_usedmoney,
			Integer score_usedmoney, Integer order_allmoney,
			Integer order_usedmoney) {
		super();
		this.days = days;
		this.oil_allmoney = oil_allmoney;
		this.notoil_allmoney = notoil_allmoney;
		this.score_allmoney = score_allmoney;
		this.oil_usedmoney = oil_usedmoney;
		this.notoil_usedmoney = notoil_usedmoney;
		this.score_usedmoney = score_usedmoney;
		this.order_allmoney = order_allmoney;
		this.order_usedmoney = order_usedmoney;
	}
	@Override
	public String toString() {
		return "CouponAll [days=" + days + ", oil_allmoney=" + oil_allmoney
				+ ", notoil_allmoney=" + notoil_allmoney + ", score_allmoney="
				+ score_allmoney + ", oil_usedmoney=" + oil_usedmoney
				+ ", notoil_usedmoney=" + notoil_usedmoney
				+ ", score_usedmoney=" + score_usedmoney + ", order_allmoney="
				+ order_allmoney + ", order_usedmoney=" + order_usedmoney + "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
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
	public Integer getScore_allmoney() {
		return score_allmoney;
	}
	public void setScore_allmoney(Integer score_allmoney) {
		this.score_allmoney = score_allmoney;
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
	public Integer getScore_usedmoney() {
		return score_usedmoney;
	}
	public void setScore_usedmoney(Integer score_usedmoney) {
		this.score_usedmoney = score_usedmoney;
	}
	public Integer getOrder_allmoney() {
		return order_allmoney;
	}
	public void setOrder_allmoney(Integer order_allmoney) {
		this.order_allmoney = order_allmoney;
	}
	public Integer getOrder_usedmoney() {
		return order_usedmoney;
	}
	public void setOrder_usedmoney(Integer order_usedmoney) {
		this.order_usedmoney = order_usedmoney;
	}
	
	
}
