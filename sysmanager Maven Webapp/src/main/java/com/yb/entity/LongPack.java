package com.yb.entity;

public class LongPack {
	private String name;
	private Long value;
	public LongPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LongPack(String name, Long value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "LongPack [name=" + name + ", value=" + value + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
}
