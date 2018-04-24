package com.yb.entity;

public class OilData {
	private String date;
	private String day;
	private String minutes;
	private String stationID;
	private Double litre92;
	private Double litre95;
	private Double litre97;
	private Double litre0;
	private Double litre10;
	private Double litre20;
	
	private Double viplitre92;
	private Double viplitre95;
	private Double viplitre97;
	private Double viplitre0;
	private Double viplitre10;
	private Double viplitre20;
	
	private Double notviplitre92;
	private Double notviplitre95;
	private Double notviplitre97;
	private Double notviplitre0;
	private Double notviplitre10;
	private Double notviplitre20;
	public OilData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OilData(String date, String day, String minutes, String stationID,
			Double litre92, Double litre95, Double litre97, Double litre0,
			Double litre10, Double litre20, Double viplitre92,
			Double viplitre95, Double viplitre97, Double viplitre0,
			Double viplitre10, Double viplitre20, Double notviplitre92,
			Double notviplitre95, Double notviplitre97, Double notviplitre0,
			Double notviplitre10, Double notviplitre20) {
		super();
		this.date = date;
		this.day = day;
		this.minutes = minutes;
		this.stationID = stationID;
		this.litre92 = litre92;
		this.litre95 = litre95;
		this.litre97 = litre97;
		this.litre0 = litre0;
		this.litre10 = litre10;
		this.litre20 = litre20;
		this.viplitre92 = viplitre92;
		this.viplitre95 = viplitre95;
		this.viplitre97 = viplitre97;
		this.viplitre0 = viplitre0;
		this.viplitre10 = viplitre10;
		this.viplitre20 = viplitre20;
		this.notviplitre92 = notviplitre92;
		this.notviplitre95 = notviplitre95;
		this.notviplitre97 = notviplitre97;
		this.notviplitre0 = notviplitre0;
		this.notviplitre10 = notviplitre10;
		this.notviplitre20 = notviplitre20;
	}
	@Override
	public String toString() {
		return "OilData [date=" + date + ", day=" + day + ", minutes="
				+ minutes + ", stationID=" + stationID + ", litre92=" + litre92
				+ ", litre95=" + litre95 + ", litre97=" + litre97 + ", litre0="
				+ litre0 + ", litre10=" + litre10 + ", litre20=" + litre20
				+ ", viplitre92=" + viplitre92 + ", viplitre95=" + viplitre95
				+ ", viplitre97=" + viplitre97 + ", viplitre0=" + viplitre0
				+ ", viplitre10=" + viplitre10 + ", viplitre20=" + viplitre20
				+ ", notviplitre92=" + notviplitre92 + ", notviplitre95="
				+ notviplitre95 + ", notviplitre97=" + notviplitre97
				+ ", notviplitre0=" + notviplitre0 + ", notviplitre10="
				+ notviplitre10 + ", notviplitre20=" + notviplitre20 + "]";
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
	public Double getLitre92() {
		return litre92;
	}
	public void setLitre92(Double litre92) {
		this.litre92 = litre92;
	}
	public Double getLitre95() {
		return litre95;
	}
	public void setLitre95(Double litre95) {
		this.litre95 = litre95;
	}
	public Double getLitre97() {
		return litre97;
	}
	public void setLitre97(Double litre97) {
		this.litre97 = litre97;
	}
	public Double getLitre0() {
		return litre0;
	}
	public void setLitre0(Double litre0) {
		this.litre0 = litre0;
	}
	public Double getLitre10() {
		return litre10;
	}
	public void setLitre10(Double litre10) {
		this.litre10 = litre10;
	}
	public Double getLitre20() {
		return litre20;
	}
	public void setLitre20(Double litre20) {
		this.litre20 = litre20;
	}
	public Double getViplitre92() {
		return viplitre92;
	}
	public void setViplitre92(Double viplitre92) {
		this.viplitre92 = viplitre92;
	}
	public Double getViplitre95() {
		return viplitre95;
	}
	public void setViplitre95(Double viplitre95) {
		this.viplitre95 = viplitre95;
	}
	public Double getViplitre97() {
		return viplitre97;
	}
	public void setViplitre97(Double viplitre97) {
		this.viplitre97 = viplitre97;
	}
	public Double getViplitre0() {
		return viplitre0;
	}
	public void setViplitre0(Double viplitre0) {
		this.viplitre0 = viplitre0;
	}
	public Double getViplitre10() {
		return viplitre10;
	}
	public void setViplitre10(Double viplitre10) {
		this.viplitre10 = viplitre10;
	}
	public Double getViplitre20() {
		return viplitre20;
	}
	public void setViplitre20(Double viplitre20) {
		this.viplitre20 = viplitre20;
	}
	public Double getNotviplitre92() {
		return notviplitre92;
	}
	public void setNotviplitre92(Double notviplitre92) {
		this.notviplitre92 = notviplitre92;
	}
	public Double getNotviplitre95() {
		return notviplitre95;
	}
	public void setNotviplitre95(Double notviplitre95) {
		this.notviplitre95 = notviplitre95;
	}
	public Double getNotviplitre97() {
		return notviplitre97;
	}
	public void setNotviplitre97(Double notviplitre97) {
		this.notviplitre97 = notviplitre97;
	}
	public Double getNotviplitre0() {
		return notviplitre0;
	}
	public void setNotviplitre0(Double notviplitre0) {
		this.notviplitre0 = notviplitre0;
	}
	public Double getNotviplitre10() {
		return notviplitre10;
	}
	public void setNotviplitre10(Double notviplitre10) {
		this.notviplitre10 = notviplitre10;
	}
	public Double getNotviplitre20() {
		return notviplitre20;
	}
	public void setNotviplitre20(Double notviplitre20) {
		this.notviplitre20 = notviplitre20;
	}
	
}
