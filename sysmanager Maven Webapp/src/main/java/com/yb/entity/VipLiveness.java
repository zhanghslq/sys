package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class VipLiveness implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date month;
	private Integer zero;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer overThree;
	public VipLiveness() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipLiveness(Integer id, Date month, Integer zero, Integer one,
			Integer two, Integer three, Integer overThree) {
		super();
		this.id = id;
		this.month = month;
		this.zero = zero;
		this.one = one;
		this.two = two;
		this.three = three;
		this.overThree = overThree;
	}
	@Override
	public String toString() {
		return "VipLiveness [id=" + id + ", month=" + month + ", zero=" + zero
				+ ", one=" + one + ", two=" + two + ", three=" + three
				+ ", overThree=" + overThree + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public Integer getZero() {
		return zero;
	}
	public void setZero(Integer zero) {
		this.zero = zero;
	}
	public Integer getOne() {
		return one;
	}
	public void setOne(Integer one) {
		this.one = one;
	}
	public Integer getTwo() {
		return two;
	}
	public void setTwo(Integer two) {
		this.two = two;
	}
	public Integer getThree() {
		return three;
	}
	public void setThree(Integer three) {
		this.three = three;
	}
	public Integer getOverThree() {
		return overThree;
	}
	public void setOverThree(Integer overThree) {
		this.overThree = overThree;
	}
	
}
