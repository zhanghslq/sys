package com.yb.entity;

public class HHT {
	private Double hhtMoney;
	private Double iptMoney;
	public HHT() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HHT(Double hhtMoney, Double iptMoney) {
		super();
		this.hhtMoney = hhtMoney;
		this.iptMoney = iptMoney;
	}
	@Override
	public String toString() {
		return "HHT [hhtMoney=" + hhtMoney + ", iptMoney=" + iptMoney + "]";
	}
	public Double getHhtMoney() {
		return hhtMoney;
	}
	public void setHhtMoney(Double hhtMoney) {
		this.hhtMoney = hhtMoney;
	}
	public Double getIptMoney() {
		return iptMoney;
	}
	public void setIptMoney(Double iptMoney) {
		this.iptMoney = iptMoney;
	}
	
}
