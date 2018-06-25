package com.yb.entity;

public class FirstExpend {
	private Integer number;
	private Integer day;
	public FirstExpend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FirstExpend(Integer number, Integer day) {
		super();
		this.number = number;
		this.day = day;
	}
	@Override
	public String toString() {
		return "FirstExpend [number=" + number + ", day=" + day + "]";
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	
}
