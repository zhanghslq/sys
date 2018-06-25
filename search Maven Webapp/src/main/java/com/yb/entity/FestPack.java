package com.yb.entity;

public class FestPack {
	private Integer id;
	private String start;
	private String end;
	private String name;
	private Integer year;
	public FestPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FestPack(Integer id, String start, String end, String name,
			Integer year) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.name = name;
		this.year = year;
	}
	@Override
	public String toString() {
		return "FestPack [id=" + id + ", start=" + start + ", end=" + end
				+ ", name=" + name + ", year=" + year + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
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
	
}
