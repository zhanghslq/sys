package com.yb.entity;

import java.io.Serializable;

public class NotOilTrade implements Serializable{
	/**
	 * 非油交易
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private Integer tradeNumber;
	private Double tradeAmount;
	private Double singleAmount;
	public NotOilTrade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotOilTrade(String date, Integer tradeNumber, Double tradeAmount,
			Double singleAmount) {
		super();
		this.date = date;
		this.tradeNumber = tradeNumber;
		this.tradeAmount = tradeAmount;
		this.singleAmount = singleAmount;
	}
	@Override
	public String toString() {
		return "NotOilTrade [date=" + date + ", tradeNumber=" + tradeNumber
				+ ", tradeAmount=" + tradeAmount + ", singleAmount="
				+ singleAmount + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(Integer tradeNumber) {
		this.tradeNumber = tradeNumber;
	}
	public Double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public Double getSingleAmount() {
		return singleAmount;
	}
	public void setSingleAmount(Double singleAmount) {
		this.singleAmount = singleAmount;
	}
	
	
}
