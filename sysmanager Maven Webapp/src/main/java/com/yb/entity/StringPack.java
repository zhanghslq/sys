package com.yb.entity;

public class StringPack {
	private String name;
	private String value;
	public StringPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StringPack(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "StringPack [name=" + name + ", value=" + value + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
