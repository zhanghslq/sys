package com.yb.entity;

import java.io.Serializable;

public class VipLiveness implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String month;
	private Integer zero;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;
	private Integer overfive;
	
	public VipLiveness() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VipLiveness(String month, Integer zero, Integer one, Integer two,
			Integer three, Integer four, Integer five, Integer overfive) {
		super();
		this.month = month;
		this.zero = zero;
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		this.overfive = overfive;
	}

	@Override
	public String toString() {
		return "VipLiveness [month=" + month + ", zero=" + zero + ", one="
				+ one + ", two=" + two + ", three=" + three + ", four=" + four
				+ ", five=" + five + ", overfive=" + overfive + "]";
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
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

	public Integer getFour() {
		return four;
	}

	public void setFour(Integer four) {
		this.four = four;
	}

	public Integer getFive() {
		return five;
	}

	public void setFive(Integer five) {
		this.five = five;
	}

	public Integer getOverfive() {
		return overfive;
	}

	public void setOverfive(Integer overfive) {
		this.overfive = overfive;
	}
	
}
