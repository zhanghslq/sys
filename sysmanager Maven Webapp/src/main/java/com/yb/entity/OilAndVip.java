package com.yb.entity;

public class OilAndVip {
	private String date;
	private Integer oilNumber;
	private Double oilLitre;
	private Double avgLitre;
	private Integer vipOilNumber;
	private Double vipOilLitre;
	private Double vipAvgLitre;
	public OilAndVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OilAndVip(String date, Integer oilNumber, Double oilLitre,
			Double avgLitre, Integer vipOilNumber, Double vipOilLitre,
			Double vipAvgLitre) {
		super();
		this.date = date;
		this.oilNumber = oilNumber;
		this.oilLitre = oilLitre;
		this.avgLitre = avgLitre;
		this.vipOilNumber = vipOilNumber;
		this.vipOilLitre = vipOilLitre;
		this.vipAvgLitre = vipAvgLitre;
	}
	@Override
	public String toString() {
		return "OilAndVip [date=" + date + ", oilNumber=" + oilNumber
				+ ", oilLitre=" + oilLitre + ", avgLitre=" + avgLitre
				+ ", vipOilNumber=" + vipOilNumber + ", vipOilLitre="
				+ vipOilLitre + ", vipAvgLitre=" + vipAvgLitre + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getOilNumber() {
		return oilNumber;
	}
	public void setOilNumber(Integer oilNumber) {
		this.oilNumber = oilNumber;
	}
	public Double getOilLitre() {
		return oilLitre;
	}
	public void setOilLitre(Double oilLitre) {
		this.oilLitre = oilLitre;
	}
	public Double getAvgLitre() {
		return avgLitre;
	}
	public void setAvgLitre(Double avgLitre) {
		this.avgLitre = avgLitre;
	}
	public Integer getVipOilNumber() {
		return vipOilNumber;
	}
	public void setVipOilNumber(Integer vipOilNumber) {
		this.vipOilNumber = vipOilNumber;
	}
	public Double getVipOilLitre() {
		return vipOilLitre;
	}
	public void setVipOilLitre(Double vipOilLitre) {
		this.vipOilLitre = vipOilLitre;
	}
	public Double getVipAvgLitre() {
		return vipAvgLitre;
	}
	public void setVipAvgLitre(Double vipAvgLitre) {
		this.vipAvgLitre = vipAvgLitre;
	}
	
}
