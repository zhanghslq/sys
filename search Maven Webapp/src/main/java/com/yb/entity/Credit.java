package com.yb.entity;

public class Credit {
	private String days;
	private Double getCredits;
	private Double usedCredits;
	public Credit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Credit(String days, Double getCredits, Double usedCredits) {
		super();
		this.days = days;
		this.getCredits = getCredits;
		this.usedCredits = usedCredits;
	}
	@Override
	public String toString() {
		return "Credit [days=" + days + ", getCredits=" + getCredits
				+ ", usedCredits=" + usedCredits + "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public Double getGetCredits() {
		return getCredits;
	}
	public void setGetCredits(Double getCredits) {
		this.getCredits = getCredits;
	}
	public Double getUsedCredits() {
		return usedCredits;
	}
	public void setUsedCredits(Double usedCredits) {
		this.usedCredits = usedCredits;
	}
	
}
