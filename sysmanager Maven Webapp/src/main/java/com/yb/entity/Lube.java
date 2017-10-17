package com.yb.entity;


public class Lube{
	private String minutes;
	private Integer lubeNumber;
	private Double LubeLitre;
	private Double lubeMoney;
	private Double avgMoney;
	
	@Override
	public String toString() {
		return "Lube [minutes=" + minutes + ", lubeNumber=" + lubeNumber
				+ ", LubeLitre=" + LubeLitre + ", lubeMoney=" + lubeMoney
				+ ", avgMoney=" + avgMoney + "]";
	}
	public Lube(String minutes, Integer lubeNumber, Double lubeLitre,
			Double lubeMoney, Double avgMoney) {
		super();
		this.minutes = minutes;
		this.lubeNumber = lubeNumber;
		LubeLitre = lubeLitre;
		this.lubeMoney = lubeMoney;
		this.avgMoney = avgMoney;
	}
	public Lube() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public Integer getLubeNumber() {
		return lubeNumber;
	}
	public void setLubeNumber(Integer lubeNumber) {
		this.lubeNumber = lubeNumber;
	}
	public Double getLubeLitre() {
		return LubeLitre;
	}
	public void setLubeLitre(Double lubeLitre) {
		LubeLitre = lubeLitre;
	}
	public Double getLubeMoney() {
		return lubeMoney;
	}
	public void setLubeMoney(Double lubeMoney) {
		this.lubeMoney = lubeMoney;
	}
	public Double getAvgMoney() {
		return avgMoney;
	}
	public void setAvgMoney(Double avgMoney) {
		this.avgMoney = avgMoney;
	}
	
}
