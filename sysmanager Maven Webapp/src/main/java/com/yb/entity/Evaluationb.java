package com.yb.entity;

public class Evaluationb {
	private String days;
	private Integer PROBLEM_ID;
	private String PROBLEMTEXT;
	private Integer yes;
	private Integer no;
	private Integer unknow;
	public Evaluationb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Evaluationb(String days, Integer pROBLEM_ID, String pROBLEMTEXT,
			Integer yes, Integer no, Integer unknow) {
		super();
		this.days = days;
		PROBLEM_ID = pROBLEM_ID;
		PROBLEMTEXT = pROBLEMTEXT;
		this.yes = yes;
		this.no = no;
		this.unknow = unknow;
	}
	@Override
	public String toString() {
		return "Evaluationb [days=" + days + ", PROBLEM_ID=" + PROBLEM_ID
				+ ", PROBLEMTEXT=" + PROBLEMTEXT + ", yes=" + yes + ", no="
				+ no + ", unknow=" + unknow + "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public Integer getPROBLEM_ID() {
		return PROBLEM_ID;
	}
	public void setPROBLEM_ID(Integer pROBLEM_ID) {
		PROBLEM_ID = pROBLEM_ID;
	}
	public String getPROBLEMTEXT() {
		return PROBLEMTEXT;
	}
	public void setPROBLEMTEXT(String pROBLEMTEXT) {
		PROBLEMTEXT = pROBLEMTEXT;
	}
	public Integer getYes() {
		return yes;
	}
	public void setYes(Integer yes) {
		this.yes = yes;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getUnknow() {
		return unknow;
	}
	public void setUnknow(Integer unknow) {
		this.unknow = unknow;
	}
	
}
