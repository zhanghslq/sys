package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class OilGun implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String oilStation;
	private String gun;
	private String pump;
	private Integer tradeNumber;
	private Double tradeLitre;
	public OilGun() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OilGun(Integer id, Date date, String oilStation, String gun,
			String pump, Integer tradeNumber, Double tradeLitre) {
		super();
		this.id = id;
		this.date = date;
		this.oilStation = oilStation;
		this.gun = gun;
		this.pump = pump;
		this.tradeNumber = tradeNumber;
		this.tradeLitre = tradeLitre;
	}
	@Override
	public String toString() {
		return "OilGun [id=" + id + ", date=" + date + ", oilStation="
				+ oilStation + ", gun=" + gun + ", pump=" + pump
				+ ", tradeNumber=" + tradeNumber + ", tradeLitre=" + tradeLitre
				+ "]";
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
	public String getGun() {
		return gun;
	}
	public void setGun(String gun) {
		this.gun = gun;
	}
	public String getPump() {
		return pump;
	}
	public void setPump(String pump) {
		this.pump = pump;
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
	
}
