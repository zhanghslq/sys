package com.yb.entity;

public class VipRechargePack {
	private Double number;
	private Double amount;

	public VipRechargePack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VipRechargePack(Double number, Double amount) {
		super();
		this.number = number;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "VipRechargePack [number=" + number + ", amount=" + amount + "]";
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	
}
