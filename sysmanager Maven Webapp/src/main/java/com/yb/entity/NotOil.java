package com.yb.entity;



/**
 * 便利店的销售
 * @author Administrator
 */
public class NotOil {
	private String minutes;
	private String stationID;
	private Integer notOilNumber;
	private Double notOilMoney;
	private Double avgMoney;
	private Double exceptLube;
	public NotOil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotOil(String minutes, Integer notOilNumber, Double notOilMoney,
			Double avgMoney, Double exceptLube) {
		super();
		this.minutes = minutes;
		this.notOilNumber = notOilNumber;
		this.notOilMoney = notOilMoney;
		this.avgMoney = avgMoney;
		this.exceptLube = exceptLube;
	}
	@Override
	public String toString() {
		return "NotOil [minutes=" + minutes + ", stationID=" + stationID
				+ ", notOilNumber=" + notOilNumber + ", notOilMoney="
				+ notOilMoney + ", avgMoney=" + avgMoney + ", exceptLube="
				+ exceptLube + "]";
	}
	
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	public NotOil(String minutes, String stationID, Integer notOilNumber,
			Double notOilMoney, Double avgMoney, Double exceptLube) {
		super();
		this.minutes = minutes;
		this.stationID = stationID;
		this.notOilNumber = notOilNumber;
		this.notOilMoney = notOilMoney;
		this.avgMoney = avgMoney;
		this.exceptLube = exceptLube;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
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
	
}
