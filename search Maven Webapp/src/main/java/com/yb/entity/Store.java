package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class Store implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String productName;
	private String oilStation;
	private Date date;
	private Double amount;
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Store(Integer id, String productName, String oilStation, Date date,
			Double amount) {
		super();
		this.id = id;
		this.productName = productName;
		this.oilStation = oilStation;
		this.date = date;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", productName=" + productName
				+ ", oilStation=" + oilStation + ", date=" + date + ", amount="
				+ amount + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
