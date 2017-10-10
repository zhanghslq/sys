package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private Integer exchange;
	private Integer give;
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coupon(Integer id, Date date, Integer exchange, Integer give) {
		super();
		this.id = id;
		this.date = date;
		this.exchange = exchange;
		this.give = give;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", date=" + date + ", exchange=" + exchange
				+ ", give=" + give + "]";
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
	public Integer getExchange() {
		return exchange;
	}
	public void setExchange(Integer exchange) {
		this.exchange = exchange;
	}
	public Integer getGive() {
		return give;
	}
	public void setGive(Integer give) {
		this.give = give;
	}
	
}
