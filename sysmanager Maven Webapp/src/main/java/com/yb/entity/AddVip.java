package com.yb.entity;

import java.io.Serializable;

/**
 *
 */
public class AddVip implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private Integer number;
	private Integer totalPeople;
	public AddVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddVip(String date, Integer number, Integer totalPeople) {
		super();
		this.date = date;
		this.number = number;
		this.totalPeople = totalPeople;
	}
	@Override
	public String toString() {
		return "AddVip [date=" + date + ", number=" + number + ", totalPeople="
				+ totalPeople + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}
	
}
