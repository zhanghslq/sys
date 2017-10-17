package com.yb.entity;

/**
 * res_Oil
 * @author Administrator
 *
 */
public class Oil {
	private String minutes;
	private String stationID;
	private String oils;
	private Double oilNumber;
	private Double oilLitre;
	private Double oilMoney;
	public Oil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Oil(String minutes, String stationID, String oils, Double oilNumber,
			Double oilLitre, Double oilMoney) {
		super();
		this.minutes = minutes;
		this.stationID = stationID;
		this.oils = oils;
		this.oilNumber = oilNumber;
		this.oilLitre = oilLitre;
		this.oilMoney = oilMoney;
	}
	@Override
	public String toString() {
		return "Oil [minutes=" + minutes + ", stationID=" + stationID
				+ ", oils=" + oils + ", oilNumber=" + oilNumber + ", oilLitre="
				+ oilLitre + ", oilMoney=" + oilMoney + "]";
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
	public String getOils() {
		return oils;
	}
	public void setOils(String oils) {
		this.oils = oils;
	}
	public Double getOilNumber() {
		return oilNumber;
	}
	public void setOilNumber(Double oilNumber) {
		this.oilNumber = oilNumber;
	}
	public Double getOilLitre() {
		return oilLitre;
	}
	public void setOilLitre(Double oilLitre) {
		this.oilLitre = oilLitre;
	}
	public Double getOilMoney() {
		return oilMoney;
	}
	public void setOilMoney(Double oilMoney) {
		this.oilMoney = oilMoney;
	}
	
}
