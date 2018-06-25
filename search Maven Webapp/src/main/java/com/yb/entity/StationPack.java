package com.yb.entity;
public class StationPack {
	private String id;
	private String name;
	private String city;
	private String gasoline;
	private String diesel;
	private String location;
	private String openDate;
	private String salesArea;
	private String administraiveRegion;
	private String type;
	public StationPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StationPack(String id, String name, String city, String gasoline,
			String diesel, String location, String openDate, String salesArea,
			String administraiveRegion) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.gasoline = gasoline;
		this.diesel = diesel;
		this.location = location;
		this.openDate = openDate;
		this.salesArea = salesArea;
		this.administraiveRegion = administraiveRegion;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public StationPack(String id, String name, String city, String gasoline,
			String diesel, String location, String openDate, String salesArea,
			String administraiveRegion, String type) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.gasoline = gasoline;
		this.diesel = diesel;
		this.location = location;
		this.openDate = openDate;
		this.salesArea = salesArea;
		this.administraiveRegion = administraiveRegion;
		this.type = type;
	}
	@Override
	public String toString() {
		return "StationPack [id=" + id + ", name=" + name + ", city=" + city
				+ ", gasoline=" + gasoline + ", diesel=" + diesel
				+ ", location=" + location + ", openDate=" + openDate
				+ ", salesArea=" + salesArea + ", administraiveRegion="
				+ administraiveRegion + ", type=" + type + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGasoline() {
		return gasoline;
	}
	public void setGasoline(String gasoline) {
		this.gasoline = gasoline;
	}
	public String getDiesel() {
		return diesel;
	}
	public void setDiesel(String diesel) {
		this.diesel = diesel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getSalesArea() {
		return salesArea;
	}
	public void setSalesArea(String salesArea) {
		this.salesArea = salesArea;
	}
	public String getAdministraiveRegion() {
		return administraiveRegion;
	}
	public void setAdministraiveRegion(String administraiveRegion) {
		this.administraiveRegion = administraiveRegion;
	}
	
}
