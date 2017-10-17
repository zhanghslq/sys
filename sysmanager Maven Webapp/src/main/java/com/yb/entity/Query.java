package com.yb.entity;

public class Query {
	private String id;
	private String name;
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Query(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Query [id=" + id + ", name=" + name + "]";
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
	
}
