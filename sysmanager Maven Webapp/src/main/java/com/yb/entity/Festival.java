package com.yb.entity;

import java.util.Date;

public class Festival {
	private Integer id;
	private Date start;
	private Date end;
	private String name;
	private Integer year;
	public Festival() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Festival(Integer id, Date start, Date end, String name, Integer year) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.name = name;
		this.year = year;
	}
	@Override
	public String toString() {
		return "Festival [id=" + id + ", start=" + start + ", end=" + end
				+ ", name=" + name + ", year=" + year + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
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
