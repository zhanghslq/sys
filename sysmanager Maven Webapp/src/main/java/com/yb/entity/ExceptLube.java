package com.yb.entity;

public class ExceptLube {
	private String minutes;
	private Double money;
	private Double vipMoney;
	private Double notVipMoney;
	public ExceptLube() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExceptLube(String minutes, Double money, Double vipMoney,
			Double notVipMoney) {
		super();
		this.minutes = minutes;
		this.money = money;
		this.vipMoney = vipMoney;
		this.notVipMoney = notVipMoney;
	}
	@Override
	public String toString() {
		return "ExceptLube [minutes=" + minutes + ", money=" + money
				+ ", vipMoney=" + vipMoney + ", notVipMoney=" + notVipMoney
				+ "]";
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getVipMoney() {
		return vipMoney;
	}
	public void setVipMoney(Double vipMoney) {
		this.vipMoney = vipMoney;
	}
	public Double getNotVipMoney() {
		return notVipMoney;
	}
	public void setNotVipMoney(Double notVipMoney) {
		this.notVipMoney = notVipMoney;
	}
	
	
}
