package com.yb.entity;

import java.util.List;

public class Permission {
	private String id;
	private String name;
	private String message;
	private List<Permission> permissions;
	public Permission(String id, String name, String message,
			List<Permission> permissions) {
		super();
		this.id = id;
		this.name = name;
		this.message = message;
		this.permissions = permissions;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
