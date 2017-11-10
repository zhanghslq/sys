package com.yb.entity;

public class HomePack {
	private String date;
	private Double oilMoney;
	private Double notoilMoney;
	private Double avgWater;
	public HomePack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HomePack(String date, Double oilMoney, Double notoilMoney,
			Double avgWater) {
		super();
		this.date = date;
		this.oilMoney = oilMoney;
		this.notoilMoney = notoilMoney;
		this.avgWater = avgWater;
	}
	@Override
	public String toString() {
		return "HomePack [date=" + date + ", oilMoney=" + oilMoney
				+ ", notoilMoney=" + notoilMoney + ", avgWater=" + avgWater
				+ "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getOilMoney() {
		return oilMoney;
	}
	public void setOilMoney(Double oilMoney) {
		this.oilMoney = oilMoney;
	}
	public Double getNotoilMoney() {
		return notoilMoney;
	}
	public void setNotoilMoney(Double notoilMoney) {
		this.notoilMoney = notoilMoney;
	}
	public Double getAvgWater() {
		return avgWater;
	}
	public void setAvgWater(Double avgWater) {
		this.avgWater = avgWater;
	}
	
	
}
