package com.yb.entity;

public class DouPack {
	private String month;
	private Double drainNum;
	private Double other;
	private Double value3;

	@Override
	public String toString() {
		return "DouPack{" +
				"month='" + month + '\'' +
				", drainNum=" + drainNum +
				", other=" + other +
				", value3=" + value3 +
				'}';
	}

	public Double getValue3() {
		return value3;
	}

	public void setValue3(Double value3) {
		this.value3 = value3;
	}

	public DouPack(String month, Double drainNum, Double other, Double value3) {

		this.month = month;
		this.drainNum = drainNum;
		this.other = other;
		this.value3 = value3;
	}

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
