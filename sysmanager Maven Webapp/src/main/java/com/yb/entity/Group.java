package com.yb.entity;
/**
 * 分组之后需要展示的，包装
 * @author lenovo
 *
 */
public class Group {
	private Integer id;
	private String name;
	private Integer number;
	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Group(Integer id, String name, Integer number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", number=" + number
				+ "]";
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
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
