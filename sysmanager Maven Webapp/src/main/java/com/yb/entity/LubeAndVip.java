package com.yb.entity;

public class LubeAndVip {
	private String stationID;
	private String minutes;
	private Integer lubeNumber;
	private Double lubeMoney;
	private Double avgMoney;
	private Integer vipLubeNumber;
	private Double vipLubeMoney;
	private Double vipAvgMoney;
	public LubeAndVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public LubeAndVip(String stationID, String minutes, Integer lubeNumber,
			Double lubeMoney, Double avgMoney, Integer vipLubeNumber,
			Double vipLubeMoney, Double vipAvgMoney) {
		super();
		this.stationID = stationID;
		this.minutes = minutes;
		this.lubeNumber = lubeNumber;
		this.lubeMoney = lubeMoney;
		this.avgMoney = avgMoney;
		this.vipLubeNumber = vipLubeNumber;
		this.vipLubeMoney = vipLubeMoney;
		this.vipAvgMoney = vipAvgMoney;
	}
	@Override
	public String toString() {
		return "LubeAndVip [stationID=" + stationID + ", minutes=" + minutes
				+ ", lubeNumber=" + lubeNumber + ", lubeMoney=" + lubeMoney
				+ ", avgMoney=" + avgMoney + ", vipLubeNumber=" + vipLubeNumber
				+ ", vipLubeMoney=" + vipLubeMoney + ", vipAvgMoney="
				+ vipAvgMoney + "]";
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
	public Integer getVipLubeNumber() {
		return vipLubeNumber;
	}
	public void setVipLubeNumber(Integer vipLubeNumber) {
		this.vipLubeNumber = vipLubeNumber;
	}
	public Double getVipLubeMoney() {
		return vipLubeMoney;
	}
	public void setVipLubeMoney(Double vipLubeMoney) {
		this.vipLubeMoney = vipLubeMoney;
	}
	public Double getVipAvgMoney() {
		return vipAvgMoney;
	}
	public void setVipAvgMoney(Double vipAvgMoney) {
		this.vipAvgMoney = vipAvgMoney;
	}
	
}
