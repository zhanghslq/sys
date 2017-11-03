package com.yb.entity;

public class DouPack {
	private Double drainNum;
	private Double other;
	public DouPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DouPack(Double drainNum, Double other) {
		super();
		this.drainNum = drainNum;
		this.other = other;
	}
	@Override
	public String toString() {
		return "DouPack [drainNum=" + drainNum + ", other=" + other + "]";
	}
	public Double getDrainNum() {
		return drainNum;
	}
	public void setDrainNum(Double drainNum) {
		this.drainNum = drainNum;
	}
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
		this.other = other;
	}
	
}
