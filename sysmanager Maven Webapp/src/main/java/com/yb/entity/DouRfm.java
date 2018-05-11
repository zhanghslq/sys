package com.yb.entity;

public class DouRfm {
	private Double R;
	private Double rate;
	private Double money;
	public DouRfm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DouRfm(Double r, Double rate, Double money) {
		super();
		R = r;
		this.rate = rate;
		this.money = money;
	}
	@Override
	public String toString() {
		return "DouRfm [R=" + R + ", rate=" + rate + ", money=" + money + "]";
	}
	public Double getR() {
		return R;
	}
	public void setR(Double r) {
		R = r;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
}
