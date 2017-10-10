package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class VipNotOilTrade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String oilStation;
	private Integer tradeNumber;
	private Double tradeAmount;
	public VipNotOilTrade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipNotOilTrade(Integer id, Date date, String oilStation,
			Integer tradeNumber, Double tradeAmount) {
		super();
		this.id = id;
		this.date = date;
		this.oilStation = oilStation;
		this.tradeNumber = tradeNumber;
		this.tradeAmount = tradeAmount;
	}
	@Override
	public String toString() {
		return "VipNotOilTrade [id=" + id + ", date=" + date + ", oilStation="
				+ oilStation + ", tradeNumber=" + tradeNumber
				+ ", tradeAmount=" + tradeAmount + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOilStation() {
		return oilStation;
	}
	public void setOilStation(String oilStation) {
		this.oilStation = oilStation;
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
	
}
