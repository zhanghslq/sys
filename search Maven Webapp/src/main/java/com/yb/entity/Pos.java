package com.yb.entity;

public class Pos {
	private Integer id;
	private String pos_ip;
	public Pos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pos(Integer id, String pos_ip) {
		super();
		this.id = id;
		this.pos_ip = pos_ip;
	}
	@Override
	public String toString() {
		return "Pos [id=" + id + ", pos_ip=" + pos_ip + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPos_ip() {
		return pos_ip;
	}
	public void setPos_ip(String pos_ip) {
		this.pos_ip = pos_ip;
	}
	
}
