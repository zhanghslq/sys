package com.yb.entity;

public class NotOilAndVip {
	private String minutes;
	private Integer notOilNumber;
	private Double notOilMoney;
	private Double avgMoney;
	private Integer vipNotOilNumber;
	private Double vipNotOilMoney;
	private Double vipAvgMoney;
	private Double exceptLube;
	private Double vipExceptLube; 
	public NotOilAndVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotOilAndVip(String minutes, Integer notOilNumber,
			Double notOilMoney, Double avgMoney, Integer vipNotOilNumber,
			Double vipNotOilMoney, Double vipAvgMoney, Double exceptLube,
			Double vipExceptLube) {
		super();
		this.minutes = minutes;
		this.notOilNumber = notOilNumber;
		this.notOilMoney = notOilMoney;
		this.avgMoney = avgMoney;
		this.vipNotOilNumber = vipNotOilNumber;
		this.vipNotOilMoney = vipNotOilMoney;
		this.vipAvgMoney = vipAvgMoney;
		this.exceptLube = exceptLube;
		this.vipExceptLube = vipExceptLube;
	}
	@Override
	public String toString() {
		return "NotOilAndVip [minutes=" + minutes + ", notOilNumber="
				+ notOilNumber + ", notOilMoney=" + notOilMoney + ", avgMoney="
				+ avgMoney + ", vipNotOilNumber=" + vipNotOilNumber
				+ ", vipNotOilMoney=" + vipNotOilMoney + ", vipAvgMoney="
				+ vipAvgMoney + ", exceptLube=" + exceptLube
				+ ", vipExceptLube=" + vipExceptLube + "]";
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
	public Integer getVipNotOilNumber() {
		return vipNotOilNumber;
	}
	public void setVipNotOilNumber(Integer vipNotOilNumber) {
		this.vipNotOilNumber = vipNotOilNumber;
	}
	public Double getVipNotOilMoney() {
		return vipNotOilMoney;
	}
	public void setVipNotOilMoney(Double vipNotOilMoney) {
		this.vipNotOilMoney = vipNotOilMoney;
	}
	public Double getVipAvgMoney() {
		return vipAvgMoney;
	}
	public void setVipAvgMoney(Double vipAvgMoney) {
		this.vipAvgMoney = vipAvgMoney;
	}
	public Double getExceptLube() {
		return exceptLube;
	}
	public void setExceptLube(Double exceptLube) {
		this.exceptLube = exceptLube;
	}
	public Double getVipExceptLube() {
		return vipExceptLube;
	}
	public void setVipExceptLube(Double vipExceptLube) {
		this.vipExceptLube = vipExceptLube;
	}
	
	
	
}
