package com.yb.entity;

public class Evaluation {
	private String date;
	private String stationID;
	private Double star1;
	private Double star2;
	private Double star3;
	private Double star4;
	private Double star5;
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public Evaluation(String date, String stationID, Double star1,
			Double star2, Double star3, Double star4, Double star5) {
		super();
		this.date = date;
		this.stationID = stationID;
		this.star1 = star1;
		this.star2 = star2;
		this.star3 = star3;
		this.star4 = star4;
		this.star5 = star5;
	}

	public Evaluation(String date, Double star1, Double star2, Double star3,
			Double star4, Double star5) {
		super();
		this.date = date;
		this.star1 = star1;
		this.star2 = star2;
		this.star3 = star3;
		this.star4 = star4;
		this.star5 = star5;
	}
	@Override
	public String toString() {
		return "Evaluation [date=" + date + ", stationID=" + stationID
				+ ", star1=" + star1 + ", star2=" + star2 + ", star3=" + star3
				+ ", star4=" + star4 + ", star5=" + star5 + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getStar1() {
		return star1;
	}
	public void setStar1(Double star1) {
		this.star1 = star1;
	}
	public Double getStar2() {
		return star2;
	}
	public void setStar2(Double star2) {
		this.star2 = star2;
	}
	public Double getStar3() {
		return star3;
	}
	public void setStar3(Double star3) {
		this.star3 = star3;
	}
	public Double getStar4() {
		return star4;
	}
	public void setStar4(Double star4) {
		this.star4 = star4;
	}
	public Double getStar5() {
		return star5;
	}
	public void setStar5(Double star5) {
		this.star5 = star5;
	}
	
}
