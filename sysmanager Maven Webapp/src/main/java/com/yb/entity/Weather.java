package com.yb.entity;

public class Weather {
	public String date;
	private Double avgWater;
	private String city;
	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Weather(String date, Double avgWater, String city) {
		super();
		this.date = date;
		this.avgWater = avgWater;
		this.city = city;
	}
	@Override
	public String toString() {
		return "Weather [date=" + date + ", avgWater=" + avgWater + ", city="
				+ city + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getAvgWater() {
		return avgWater;
	}
	public void setAvgWater(Double avgWater) {
		this.avgWater = avgWater;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
