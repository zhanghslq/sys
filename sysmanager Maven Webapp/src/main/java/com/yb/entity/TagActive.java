package com.yb.entity;

public class TagActive {
	private Integer id;
	private String name;
	private String area;
	private String description;
	public TagActive() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TagActive(Integer id, String name, String area, String description) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.description = description;
	}
	@Override
	public String toString() {
		return "TagActive [id=" + id + ", name=" + name + ", area=" + area
				+ ", description=" + description + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
