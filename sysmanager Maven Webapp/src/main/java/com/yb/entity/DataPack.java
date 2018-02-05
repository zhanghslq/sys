package com.yb.entity;

public class DataPack {
	private String name;
	private Double value;
	private String stationID;
	public DataPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DataPack(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	public DataPack(String name, Double value, String stationID) {
		super();
		this.name = name;
		this.value = value;
		this.stationID = stationID;
	}
	@Override
	public String toString() {
		return "DataPack [name=" + name + ", value=" + value + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
