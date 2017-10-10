package com.yb.entity;

import java.io.Serializable;

public class Station implements Serializable{
	/**
	 * 添加了类别和标签
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String city;
	private Category category;
	private Tag tag;
	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Station(String id, String name, String city, Category category,
			Tag tag) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.category = category;
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + ", city=" + city
				+ ", category=" + category + ", tag=" + tag + "]";
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
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
