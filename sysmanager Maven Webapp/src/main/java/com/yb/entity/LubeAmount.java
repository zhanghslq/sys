package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class LubeAmount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String oilStation;
	private Date date;
	private Integer tradeNumber;
	private Double tradeAmount;
	public LubeAmount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LubeAmount(Integer id, String oilStation, Date date,
			Integer tradeNumber, Double tradeAmount) {
		super();
		this.id = id;
		this.oilStation = oilStation;
		this.date = date;
		this.tradeNumber = tradeNumber;
		this.tradeAmount = tradeAmount;
	}
	@Override
	public String toString() {
		return "LubeAmount [id=" + id + ", oilStation=" + oilStation
				+ ", date=" + date + ", tradeNumber=" + tradeNumber
				+ ", tradeAmount=" + tradeAmount + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOilStation() {
		return oilStation;
	}
	public void setOilStation(String oilStation) {
		this.oilStation = oilStation;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	
}
