package com.yb.entity;


public class Coupon{
	/**
	 * 
	 */
	private String days;
	private Double allMoney;
	private Double usedMoney;
	private Double rebateMoney;
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coupon(String days, Double allMoney, Double usedMoney,
			Double rebateMoney) {
		super();
		this.days = days;
		this.allMoney = allMoney;
		this.usedMoney = usedMoney;
		this.rebateMoney = rebateMoney;
	}
	@Override
	public String toString() {
		return "Coupon [days=" + days + ", allMoney=" + allMoney
				+ ", usedMoney=" + usedMoney + ", rebateMoney=" + rebateMoney
				+ "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public Double getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(Double allMoney) {
		this.allMoney = allMoney;
	}
	public Double getUsedMoney() {
		return usedMoney;
	}
	public void setUsedMoney(Double usedMoney) {
		this.usedMoney = usedMoney;
	}
	public Double getRebateMoney() {
		return rebateMoney;
	}
	public void setRebateMoney(Double rebateMoney) {
		this.rebateMoney = rebateMoney;
	}
}
