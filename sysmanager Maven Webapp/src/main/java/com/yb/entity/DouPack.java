package com.yb.entity;

public class DouPack {
	private String month;
	private Double drainNum;
	private Double other;
	public DouPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DouPack(String month, Double drainNum, Double other) {
		super();
		this.month = month;
		this.drainNum = drainNum;
		this.other = other;
	}
	@Override
	public String toString() {
		return "DouPack [month=" + month + ", drainNum=" + drainNum
				+ ", other=" + other + "]";
	}
	public Double getDrainNum() {
		return drainNum;
	}
	public void setDrainNum(Double drainNum) {
		this.drainNum = drainNum;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
		this.other = other;
	}
	
}
