package com.yb.entity;

import java.math.BigDecimal;

public class NotOil {
	private String minutes;
	private Integer notOilNumber;
	private BigDecimal notOilMoney;
	private BigDecimal avgMoney;
	public NotOil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotOil(String minutes, Integer notOilNumber, BigDecimal notOilMoney,
			BigDecimal avgMoney) {
		super();
		this.minutes = minutes;
		this.notOilNumber = notOilNumber;
		this.notOilMoney = notOilMoney;
		this.avgMoney = avgMoney;
	}
	@Override
	public String toString() {
		return "NotOil [minutes=" + minutes + ", notOilNumber=" + notOilNumber
				+ ", notOilMoney=" + notOilMoney + ", avgMoney=" + avgMoney
				+ "]";
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public Integer getNotOilNumber() {
		return notOilNumber;
	}
	public void setNotOilNumber(Integer notOilNumber) {
		this.notOilNumber = notOilNumber;
	}
	public BigDecimal getNotOilMoney() {
		return notOilMoney;
	}
	public void setNotOilMoney(BigDecimal notOilMoney) {
		this.notOilMoney = notOilMoney;
	}
	public BigDecimal getAvgMoney() {
		return avgMoney;
	}
	public void setAvgMoney(BigDecimal avgMoney) {
		this.avgMoney = avgMoney;
	}
	
}
