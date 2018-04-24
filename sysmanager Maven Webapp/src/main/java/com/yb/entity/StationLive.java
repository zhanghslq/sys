package com.yb.entity;
/**
 * 油站运营天数
 * @author lenovo
 *
 */
public class StationLive {
	private String id;
	private String name;
	private Integer year;
	private Integer month;
	public StationLive() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StationLive(String id, String name, Integer year, Integer month) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.month = month;
	}
	@Override
	public String toString() {
		return "StationLive [id=" + id + ", name=" + name + ", year=" + year
				+ ", month=" + month + "]";
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
}
