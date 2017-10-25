package com.yb.entity;

import java.math.BigDecimal;

public class NotOil {
	private String minutes;
	private Integer notOilNumber;
	private Double notOilMoney;
	private Double avgMoney;
	public NotOil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotOil(String minutes, Integer notOilNumber, Double notOilMoney,
			Double avgMoney) {
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
	public Double getNotOilMoney() {
		return notOilMoney;
	}
	public void setNotOilMoney(Double notOilMoney) {
		this.notOilMoney = notOilMoney;
	}
	public Double getAvgMoney() {
		return avgMoney;
	}
	public void setAvgMoney(Double avgMoney) {
		this.avgMoney = avgMoney;
	}
	
}
