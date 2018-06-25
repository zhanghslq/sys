package com.yb.entity;

public class ExceptLube {
	private String minutes;
	private String day;
	private String minu;
	private String stationID;
	private Double money;
	private Double vipMoney;
	private Double notVipMoney;
	public ExceptLube() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExceptLube(String minutes, String day, String minu,
			String stationID, Double money, Double vipMoney, Double notVipMoney) {
		super();
		this.minutes = minutes;
		this.day = day;
		this.minu = minu;
		this.stationID = stationID;
		this.money = money;
		this.vipMoney = vipMoney;
		this.notVipMoney = notVipMoney;
	}
	@Override
	public String toString() {
		return "ExceptLube [minutes=" + minutes + ", day=" + day + ", minu="
				+ minu + ", stationID=" + stationID + ", money=" + money
				+ ", vipMoney=" + vipMoney + ", notVipMoney=" + notVipMoney
				+ "]";
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getVipMoney() {
		return vipMoney;
	}
	public void setVipMoney(Double vipMoney) {
		this.vipMoney = vipMoney;
	}
	public Double getNotVipMoney() {
		return notVipMoney;
	}
	public void setNotVipMoney(Double notVipMoney) {
		this.notVipMoney = notVipMoney;
	}
	
	
	
}
