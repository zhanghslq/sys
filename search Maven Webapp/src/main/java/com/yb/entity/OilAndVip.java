package com.yb.entity;

public class OilAndVip {
	private String date;
	private String day;
	private String minutes;
	private String stationID;
	private Integer oilNumber;
	private Double oilLitre;
	private Double avgLitre;
	private Double oilMoney;
	private Integer vipOilNumber;
	private Double vipOilLitre;
	private Double vipAvgLitre;
	private Double vipOilMoney;
	private Integer notVipOilNumber;
	private Double notVipOilLitre;
	private Double notVipAvgLitre;
	private Double notVipOilMoney;
	public OilAndVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OilAndVip(String date, String day, String minutes, String stationID,
			Integer oilNumber, Double oilLitre, Double avgLitre,
			Double oilMoney, Integer vipOilNumber, Double vipOilLitre,
			Double vipAvgLitre, Double vipOilMoney, Integer notVipOilNumber,
			Double notVipOilLitre, Double notVipAvgLitre, Double notVipOilMoney) {
		super();
		this.date = date;
		this.day = day;
		this.minutes = minutes;
		this.stationID = stationID;
		this.oilNumber = oilNumber;
		this.oilLitre = oilLitre;
		this.avgLitre = avgLitre;
		this.oilMoney = oilMoney;
		this.vipOilNumber = vipOilNumber;
		this.vipOilLitre = vipOilLitre;
		this.vipAvgLitre = vipAvgLitre;
		this.vipOilMoney = vipOilMoney;
		this.notVipOilNumber = notVipOilNumber;
		this.notVipOilLitre = notVipOilLitre;
		this.notVipAvgLitre = notVipAvgLitre;
		this.notVipOilMoney = notVipOilMoney;
	}
	@Override
	public String toString() {
		return "OilAndVip [date=" + date + ", day=" + day + ", minutes="
				+ minutes + ", stationID=" + stationID + ", oilNumber="
				+ oilNumber + ", oilLitre=" + oilLitre + ", avgLitre="
				+ avgLitre + ", oilMoney=" + oilMoney + ", vipOilNumber="
				+ vipOilNumber + ", vipOilLitre=" + vipOilLitre
				+ ", vipAvgLitre=" + vipAvgLitre + ", vipOilMoney="
				+ vipOilMoney + ", notVipOilNumber=" + notVipOilNumber
				+ ", notVipOilLitre=" + notVipOilLitre + ", notVipAvgLitre="
				+ notVipAvgLitre + ", notVipOilMoney=" + notVipOilMoney + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
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
	public Double getOilMoney() {
		return oilMoney;
	}
	public void setOilMoney(Double oilMoney) {
		this.oilMoney = oilMoney;
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
	public Double getVipOilMoney() {
		return vipOilMoney;
	}
	public void setVipOilMoney(Double vipOilMoney) {
		this.vipOilMoney = vipOilMoney;
	}
	public Integer getNotVipOilNumber() {
		return notVipOilNumber;
	}
	public void setNotVipOilNumber(Integer notVipOilNumber) {
		this.notVipOilNumber = notVipOilNumber;
	}
	public Double getNotVipOilLitre() {
		return notVipOilLitre;
	}
	public void setNotVipOilLitre(Double notVipOilLitre) {
		this.notVipOilLitre = notVipOilLitre;
	}
	public Double getNotVipAvgLitre() {
		return notVipAvgLitre;
	}
	public void setNotVipAvgLitre(Double notVipAvgLitre) {
		this.notVipAvgLitre = notVipAvgLitre;
	}
	public Double getNotVipOilMoney() {
		return notVipOilMoney;
	}
	public void setNotVipOilMoney(Double notVipOilMoney) {
		this.notVipOilMoney = notVipOilMoney;
	}
	
}
