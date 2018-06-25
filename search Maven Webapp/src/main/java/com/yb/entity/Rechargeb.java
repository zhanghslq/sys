package com.yb.entity;

public class Rechargeb {
	private String date;
	private Integer jdtradeNumber;
	private Double jdtotalAmount;
	private Integer wxtradeNumber;
	private Double wxtotalAmount;
	public Rechargeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rechargeb(String date, Integer jdtradeNumber, Double jdtotalAmount,
			Integer wxtradeNumber, Double wxtotalAmount) {
		super();
		this.date = date;
		this.jdtradeNumber = jdtradeNumber;
		this.jdtotalAmount = jdtotalAmount;
		this.wxtradeNumber = wxtradeNumber;
		this.wxtotalAmount = wxtotalAmount;
	}
	@Override
	public String toString() {
		return "Rechargeb [date=" + date + ", jdtradeNumber=" + jdtradeNumber
				+ ", jdtotalAmount=" + jdtotalAmount + ", wxtradeNumber="
				+ wxtradeNumber + ", wxtotalAmount=" + wxtotalAmount + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getJdtradeNumber() {
		return jdtradeNumber;
	}
	public void setJdtradeNumber(Integer jdtradeNumber) {
		this.jdtradeNumber = jdtradeNumber;
	}
	public Double getJdtotalAmount() {
		return jdtotalAmount;
	}
	public void setJdtotalAmount(Double jdtotalAmount) {
		this.jdtotalAmount = jdtotalAmount;
	}
	public Integer getWxtradeNumber() {
		return wxtradeNumber;
	}
	public void setWxtradeNumber(Integer wxtradeNumber) {
		this.wxtradeNumber = wxtradeNumber;
	}
	public Double getWxtotalAmount() {
		return wxtotalAmount;
	}
	public void setWxtotalAmount(Double wxtotalAmount) {
		this.wxtotalAmount = wxtotalAmount;
	}
	
}
