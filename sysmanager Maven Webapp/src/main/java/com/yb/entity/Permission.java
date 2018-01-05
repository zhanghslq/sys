package com.yb.entity;

public class Permission {
	private String id;
	private String name;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Permission(String id, String name, String message) {
		super();
		this.id = id;
		this.name = name;
		this.message = message;
	}
	public Permission(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + "]";
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
