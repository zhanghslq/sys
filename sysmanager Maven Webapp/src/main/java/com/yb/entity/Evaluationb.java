package com.yb.entity;

public class Evaluationb {
	private String days;
	private String stationID;
	private Integer PROBLEM_ID;
	private String PROBLEMTEXT;
	private Double yes;
	private Double no;
	private Double unknow;
	private String yesString;
	private String noString;
	private String unknowString;
	public Evaluationb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Evaluationb(String days, String stationID, Integer pROBLEM_ID,
			String pROBLEMTEXT, Double yes, Double no, Double unknow,
			String yesString, String noString, String unknowString) {
		super();
		this.days = days;
		this.stationID = stationID;
		PROBLEM_ID = pROBLEM_ID;
		PROBLEMTEXT = pROBLEMTEXT;
		this.yes = yes;
		this.no = no;
		this.unknow = unknow;
		this.yesString = yesString;
		this.noString = noString;
		this.unknowString = unknowString;
	}
	@Override
	public String toString() {
		return "Evaluationb [days=" + days + ", stationID=" + stationID
				+ ", PROBLEM_ID=" + PROBLEM_ID + ", PROBLEMTEXT=" + PROBLEMTEXT
				+ ", yes=" + yes + ", no=" + no + ", unknow=" + unknow
				+ ", yesString=" + yesString + ", noString=" + noString
				+ ", unknowString=" + unknowString + "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
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
	public Double getYes() {
		return yes;
	}
	public void setYes(Double yes) {
		this.yes = yes;
	}
	public Double getNo() {
		return no;
	}
	public void setNo(Double no) {
		this.no = no;
	}
	public Double getUnknow() {
		return unknow;
	}
	public void setUnknow(Double unknow) {
		this.unknow = unknow;
	}
	public String getYesString() {
		return yesString;
	}
	public void setYesString(String yesString) {
		this.yesString = yesString;
	}
	public String getNoString() {
		return noString;
	}
	public void setNoString(String noString) {
		this.noString = noString;
	}
	public String getUnknowString() {
		return unknowString;
	}
	public void setUnknowString(String unknowString) {
		this.unknowString = unknowString;
	}
	
}
