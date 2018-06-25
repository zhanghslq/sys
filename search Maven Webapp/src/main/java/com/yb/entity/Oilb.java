package com.yb.entity;

public class Oilb {
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
	public Oilb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Oilb(String date, String day, String minutes, String stationID,
			Double litre92, Double litre95, Double litre97, Double litre0,
			Double litre10, Double litre20) {
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
	}
	@Override
	public String toString() {
		return "Oilb [date=" + date + ", day=" + day + ", minutes=" + minutes
				+ ", stationID=" + stationID + ", litre92=" + litre92
				+ ", litre95=" + litre95 + ", litre97=" + litre97 + ", litre0="
				+ litre0 + ", litre10=" + litre10 + ", litre20=" + litre20
				+ "]";
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
	
	
	
}
