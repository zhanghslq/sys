package com.yb.entity;

import java.util.List;

public class StationPack {
	private String id;
	private String name;
	private String city;
	private Category category;
	private List<Integer> tags;
	public StationPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StationPack(String id, String name, String city, Category category,
			List<Integer> tags) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.category = category;
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "StationPack [id=" + id + ", name=" + name + ", city=" + city
				+ ", category=" + category + ", tags=" + tags + "]";
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Integer> getTags() {
		return tags;
	}
	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}
}
