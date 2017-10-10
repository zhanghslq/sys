package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class VipRefuel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String oilStation;
	private Integer lessThanTen;
	private Integer tenToTwenty;
	private Integer twentyToThirty;
	private Integer thirtyToFourty;
	private Integer fourtyToFifty;
	private Integer overFifty;
	public VipRefuel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipRefuel(Integer id, Date date, String oilStation,
			Integer lessThanTen, Integer tenToTwenty, Integer twentyToThirty,
			Integer thirtyToFourty, Integer fourtyToFifty, Integer overFifty) {
		super();
		this.id = id;
		this.date = date;
		this.oilStation = oilStation;
		this.lessThanTen = lessThanTen;
		this.tenToTwenty = tenToTwenty;
		this.twentyToThirty = twentyToThirty;
		this.thirtyToFourty = thirtyToFourty;
		this.fourtyToFifty = fourtyToFifty;
		this.overFifty = overFifty;
	}
	@Override
	public String toString() {
		return "VipRefuel [id=" + id + ", date=" + date + ", oilStation="
				+ oilStation + ", lessThanTen=" + lessThanTen
				+ ", tenToTwenty=" + tenToTwenty + ", twentyToThirty="
				+ twentyToThirty + ", thirtyToFourty=" + thirtyToFourty
				+ ", fourtyToFifty=" + fourtyToFifty + ", overFifty="
				+ overFifty + "]";
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
	public Integer getLessThanTen() {
		return lessThanTen;
	}
	public void setLessThanTen(Integer lessThanTen) {
		this.lessThanTen = lessThanTen;
	}
	public Integer getTenToTwenty() {
		return tenToTwenty;
	}
	public void setTenToTwenty(Integer tenToTwenty) {
		this.tenToTwenty = tenToTwenty;
	}
	public Integer getTwentyToThirty() {
		return twentyToThirty;
	}
	public void setTwentyToThirty(Integer twentyToThirty) {
		this.twentyToThirty = twentyToThirty;
	}
	public Integer getThirtyToFourty() {
		return thirtyToFourty;
	}
	public void setThirtyToFourty(Integer thirtyToFourty) {
		this.thirtyToFourty = thirtyToFourty;
	}
	public Integer getFourtyToFifty() {
		return fourtyToFifty;
	}
	public void setFourtyToFifty(Integer fourtyToFifty) {
		this.fourtyToFifty = fourtyToFifty;
	}
	public Integer getOverFifty() {
		return overFifty;
	}
	public void setOverFifty(Integer overFifty) {
		this.overFifty = overFifty;
	}
	
}
