package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class PayType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String oilStation;
	private String payType;
	private Integer number;
	public PayType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PayType(Integer id, Date date, String oilStation, String payType,
			Integer number) {
		super();
		this.id = id;
		this.date = date;
		this.oilStation = oilStation;
		this.payType = payType;
		this.number = number;
	}
	@Override
	public String toString() {
		return "PayType [id=" + id + ", date=" + date + ", oilStation="
				+ oilStation + ", payType=" + payType + ", number=" + number
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
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
