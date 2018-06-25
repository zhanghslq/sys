package com.yb.entity;

public class RolePack {
	private String id;
	private String text;
	private boolean selected;
	public RolePack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RolePack(String id, String text, boolean selected) {
		super();
		this.id = id;
		this.text = text;
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "RolePack [id=" + id + ", text=" + text + ", selected="
				+ selected + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
