package com.yb.entity;

public class NotOilAndVip {
	private String minutes;
	private String day;
	private String minu;
	private String stationID;
	private Integer notOilNumber;
	private Double notOilMoney;
	private Double avgMoney;
	private Double exceptLube;
	private Integer vipNotOilNumber;
	private Double vipNotOilMoney;
	private Double vipAvgMoney;
	private Double vipExceptLube; 
	private Integer notVipNotOilNumber;
	private Double notVipNotOilMoney;
	private Double notVipAvgMoney;
	private Double notVipExceptLube; 
	public NotOilAndVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotOilAndVip(String minutes, String day, String minu,
			String stationID, Integer notOilNumber, Double notOilMoney,
			Double avgMoney, Double exceptLube, Integer vipNotOilNumber,
			Double vipNotOilMoney, Double vipAvgMoney, Double vipExceptLube,
			Integer notVipNotOilNumber, Double notVipNotOilMoney,
			Double notVipAvgMoney, Double notVipExceptLube) {
		super();
		this.minutes = minutes;
		this.day = day;
		this.minu = minu;
		this.stationID = stationID;
		this.notOilNumber = notOilNumber;
		this.notOilMoney = notOilMoney;
		this.avgMoney = avgMoney;
		this.exceptLube = exceptLube;
		this.vipNotOilNumber = vipNotOilNumber;
		this.vipNotOilMoney = vipNotOilMoney;
		this.vipAvgMoney = vipAvgMoney;
		this.vipExceptLube = vipExceptLube;
		this.notVipNotOilNumber = notVipNotOilNumber;
		this.notVipNotOilMoney = notVipNotOilMoney;
		this.notVipAvgMoney = notVipAvgMoney;
		this.notVipExceptLube = notVipExceptLube;
	}
	@Override
	public String toString() {
		return "NotOilAndVip [minutes=" + minutes + ", day=" + day + ", minu="
				+ minu + ", stationID=" + stationID + ", notOilNumber="
				+ notOilNumber + ", notOilMoney=" + notOilMoney + ", avgMoney="
				+ avgMoney + ", exceptLube=" + exceptLube
				+ ", vipNotOilNumber=" + vipNotOilNumber + ", vipNotOilMoney="
				+ vipNotOilMoney + ", vipAvgMoney=" + vipAvgMoney
				+ ", vipExceptLube=" + vipExceptLube + ", notVipNotOilNumber="
				+ notVipNotOilNumber + ", notVipNotOilMoney="
				+ notVipNotOilMoney + ", notVipAvgMoney=" + notVipAvgMoney
				+ ", notVipExceptLube=" + notVipExceptLube + "]";
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMinu() {
		return minu;
	}
	public void setMinu(String minu) {
		this.minu = minu;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	public Integer getNotOilNumber() {
		return notOilNumber;
	}
	public void setNotOilNumber(Integer notOilNumber) {
		this.notOilNumber = notOilNumber;
	}
	public Double getNotOilMoney() {
		return notOilMoney;
	}
	public void setNotOilMoney(Double notOilMoney) {
		this.notOilMoney = notOilMoney;
	}
	public Double getAvgMoney() {
		return avgMoney;
	}
	public void setAvgMoney(Double avgMoney) {
		this.avgMoney = avgMoney;
	}
	public Double getExceptLube() {
		return exceptLube;
	}
	public void setExceptLube(Double exceptLube) {
		this.exceptLube = exceptLube;
	}
	public Integer getVipNotOilNumber() {
		return vipNotOilNumber;
	}
	public void setVipNotOilNumber(Integer vipNotOilNumber) {
		this.vipNotOilNumber = vipNotOilNumber;
	}
	public Double getVipNotOilMoney() {
		return vipNotOilMoney;
	}
	public void setVipNotOilMoney(Double vipNotOilMoney) {
		this.vipNotOilMoney = vipNotOilMoney;
	}
	public Double getVipAvgMoney() {
		return vipAvgMoney;
	}
	public void setVipAvgMoney(Double vipAvgMoney) {
		this.vipAvgMoney = vipAvgMoney;
	}
	public Double getVipExceptLube() {
		return vipExceptLube;
	}
	public void setVipExceptLube(Double vipExceptLube) {
		this.vipExceptLube = vipExceptLube;
	}
	public Integer getNotVipNotOilNumber() {
		return notVipNotOilNumber;
	}
	public void setNotVipNotOilNumber(Integer notVipNotOilNumber) {
		this.notVipNotOilNumber = notVipNotOilNumber;
	}
	public Double getNotVipNotOilMoney() {
		return notVipNotOilMoney;
	}
	public void setNotVipNotOilMoney(Double notVipNotOilMoney) {
		this.notVipNotOilMoney = notVipNotOilMoney;
	}
	public Double getNotVipAvgMoney() {
		return notVipAvgMoney;
	}
	public void setNotVipAvgMoney(Double notVipAvgMoney) {
		this.notVipAvgMoney = notVipAvgMoney;
	}
	public Double getNotVipExceptLube() {
		return notVipExceptLube;
	}
	public void setNotVipExceptLube(Double notVipExceptLube) {
		this.notVipExceptLube = notVipExceptLube;
	}
	
}
