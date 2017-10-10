package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class VipTradeAmount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String oilStation;
	private String oilName;
	private Integer tradeNumber;
	private Double tradeLitre;
	private Double tradeAmount;
	public VipTradeAmount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipTradeAmount(Integer id, Date date, String oilStation,
			String oilName, Integer tradeNumber, Double tradeLitre,
			Double tradeAmount) {
		super();
		this.id = id;
		this.date = date;
		this.oilStation = oilStation;
		this.oilName = oilName;
		this.tradeNumber = tradeNumber;
		this.tradeLitre = tradeLitre;
		this.tradeAmount = tradeAmount;
	}
	@Override
	public String toString() {
		return "VipTradeAmount [id=" + id + ", date=" + date + ", oilStation="
				+ oilStation + ", oilName=" + oilName + ", tradeNumber="
				+ tradeNumber + ", tradeLitre=" + tradeLitre + ", tradeAmount="
				+ tradeAmount + "]";
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
	public String getOilName() {
		return oilName;
	}
	public void setOilName(String oilName) {
		this.oilName = oilName;
	}
	public Integer getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(Integer tradeNumber) {
		this.tradeNumber = tradeNumber;
	}
	public Double getTradeLitre() {
		return tradeLitre;
	}
	public void setTradeLitre(Double tradeLitre) {
		this.tradeLitre = tradeLitre;
	}
	public Double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
}
