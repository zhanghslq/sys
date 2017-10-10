package com.yb.entity;

import java.io.Serializable;

public class Recharge implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String date;
	private Double totalAmount;
	private Double avgAmount;
	private Integer tradeNumber;
	public Recharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Recharge(Integer id, String date, Double totalAmount,
			Double avgAmount, Integer tradeNumber) {
		super();
		this.id = id;
		this.date = date;
		this.totalAmount = totalAmount;
		this.avgAmount = avgAmount;
		this.tradeNumber = tradeNumber;
	}
	@Override
	public String toString() {
		return "Recharge [id=" + id + ", date=" + date + ", totalAmount="
				+ totalAmount + ", avgAmount=" + avgAmount + ", tradeNumber="
				+ tradeNumber + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getAvgAmount() {
		return avgAmount;
	}
	public void setAvgAmount(Double avgAmount) {
		this.avgAmount = avgAmount;
	}
	public Integer getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(Integer tradeNumber) {
		this.tradeNumber = tradeNumber;
	}
	
	
}
