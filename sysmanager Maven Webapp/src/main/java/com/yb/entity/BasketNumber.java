package com.yb.entity;

public class BasketNumber {
	private Long number_1;
	private Long number_multi;
	private Long number;
	public BasketNumber() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BasketNumber(Long number_1, Long number_multi, Long number) {
		super();
		this.number_1 = number_1;
		this.number_multi = number_multi;
		this.number = number;
	}
	@Override
	public String toString() {
		return "BasketNumber [number_1=" + number_1 + ", number_multi="
				+ number_multi + ", number=" + number + "]";
	}
	public Long getNumber_1() {
		return number_1;
	}
	public void setNumber_1(Long number_1) {
		this.number_1 = number_1;
	}
	public Long getNumber_multi() {
		return number_multi;
	}
	public void setNumber_multi(Long number_multi) {
		this.number_multi = number_multi;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
}
