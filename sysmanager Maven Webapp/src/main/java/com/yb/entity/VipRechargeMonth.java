package com.yb.entity;

public class VipRechargeMonth {
	private String date;
	private Integer peoples;
	private Double avgRecharge;
	private Double rechargeTotal;
	public VipRechargeMonth() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipRechargeMonth(String date, Integer peoples, Double avgRecharge,
			Double rechargeTotal) {
		super();
		this.date = date;
		this.peoples = peoples;
		this.avgRecharge = avgRecharge;
		this.rechargeTotal = rechargeTotal;
	}
	@Override
	public String toString() {
		return "VipRechargeMonth [date=" + date + ", peoples=" + peoples
				+ ", avgRecharge=" + avgRecharge + ", rechargeTotal="
				+ rechargeTotal + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getPeoples() {
		return peoples;
	}
	public void setPeoples(Integer peoples) {
		this.peoples = peoples;
	}
	public Double getAvgRecharge() {
		return avgRecharge;
	}
	public void setAvgRecharge(Double avgRecharge) {
		this.avgRecharge = avgRecharge;
	}
	public Double getRechargeTotal() {
		return rechargeTotal;
	}
	public void setRechargeTotal(Double rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}
	
}
