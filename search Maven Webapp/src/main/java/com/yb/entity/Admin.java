package com.yb.entity;

public class Admin {
	private String id;
	private String name;
	private String password;
	private String salt;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String id, String name, String password, String salt) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password
				+ ", salt=" + salt + "]";
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
