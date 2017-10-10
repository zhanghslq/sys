package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class OilPrice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String oilName;
	private Double price;
	public OilPrice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OilPrice(Integer id, Date date, String oilName, Double price) {
		super();
		this.id = id;
		this.date = date;
		this.oilName = oilName;
		this.price = price;
	}
	@Override
	public String toString() {
		return "OilPrice [id=" + id + ", date=" + date + ", oilName=" + oilName
				+ ", price=" + price + "]";
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
	public String getOilName() {
		return oilName;
	}
	public void setOilName(String oilName) {
		this.oilName = oilName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
