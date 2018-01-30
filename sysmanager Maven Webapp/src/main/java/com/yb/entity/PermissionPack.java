package com.yb.entity;

import java.util.List;

public class PermissionPack {
	private String id;
	private String text;
	private Boolean checked;
	private List<PermissionPack> children;
	public PermissionPack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PermissionPack(String id, String text, Boolean checked,
			List<PermissionPack> children) {
		super();
		this.id = id;
		this.text = text;
		this.checked = checked;
		this.children = children;
	}
	@Override
	public String toString() {
		return "PermissionPack [id=" + id + ", text=" + text + ", checked="
				+ checked + ", children=" + children + "]";
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
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public List<PermissionPack> getChildren() {
		return children;
	}
	public void setChildren(List<PermissionPack> children) {
		this.children = children;
	}
	
	
}
