package com.yb.entity;

public class InterPack {
	private String name;
	private Integer value;
	public InterPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InterPack(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "InterPack [name=" + name + ", value=" + value + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}
