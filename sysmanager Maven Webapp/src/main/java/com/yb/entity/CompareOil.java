package com.yb.entity;

public class CompareOil {
	private String stationID;
	private Double beforeLitre;
	private Double afterLitre;
	private Double litreRate;
	private Double beforeNumber;
	private Double afterNumber;
	private Double numberRate;
	private Double beforeAvgLitre;
	private Double afterAvgLitre;
	private Double avgLitreRate;
	public CompareOil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompareOil(Double beforeLitre, Double afterLitre, Double litreRate,
			Double beforeNumber, Double afterNumber, Double numberRate,
			Double beforeAvgLitre, Double afterAvgLitre, Double avgLitreRate) {
		super();
		this.beforeLitre = beforeLitre;
		this.afterLitre = afterLitre;
		this.litreRate = litreRate;
		this.beforeNumber = beforeNumber;
		this.afterNumber = afterNumber;
		this.numberRate = numberRate;
		this.beforeAvgLitre = beforeAvgLitre;
		this.afterAvgLitre = afterAvgLitre;
		this.avgLitreRate = avgLitreRate;
	}
	@Override
	public String toString() {
		return "CompareOil [stationID=" + stationID + ", beforeLitre="
				+ beforeLitre + ", afterLitre=" + afterLitre + ", litreRate="
				+ litreRate + ", beforeNumber=" + beforeNumber
				+ ", afterNumber=" + afterNumber + ", numberRate=" + numberRate
				+ ", beforeAvgLitre=" + beforeAvgLitre + ", afterAvgLitre="
				+ afterAvgLitre + ", avgLitreRate=" + avgLitreRate + "]";
	}
	public Double getBeforeLitre() {
		return beforeLitre;
	}
	public void setBeforeLitre(Double beforeLitre) {
		this.beforeLitre = beforeLitre;
	}
	public Double getAfterLitre() {
		return afterLitre;
	}
	public void setAfterLitre(Double afterLitre) {
		this.afterLitre = afterLitre;
	}
	public Double getLitreRate() {
		return litreRate;
	}
	public void setLitreRate(Double litreRate) {
		this.litreRate = litreRate;
	}
	public Double getBeforeNumber() {
		return beforeNumber;
	}
	public void setBeforeNumber(Double beforeNumber) {
		this.beforeNumber = beforeNumber;
	}
	public Double getAfterNumber() {
		return afterNumber;
	}
	public void setAfterNumber(Double afterNumber) {
		this.afterNumber = afterNumber;
	}
	public Double getNumberRate() {
		return numberRate;
	}
	public void setNumberRate(Double numberRate) {
		this.numberRate = numberRate;
	}
	public Double getBeforeAvgLitre() {
		return beforeAvgLitre;
	}
	public void setBeforeAvgLitre(Double beforeAvgLitre) {
		this.beforeAvgLitre = beforeAvgLitre;
	}
	public Double getAfterAvgLitre() {
		return afterAvgLitre;
	}
	public void setAfterAvgLitre(Double afterAvgLitre) {
		this.afterAvgLitre = afterAvgLitre;
	}
	public Double getAvgLitreRate() {
		return avgLitreRate;
	}
	public void setAvgLitreRate(Double avgLitreRate) {
		this.avgLitreRate = avgLitreRate;
	}
	public CompareOil(String stationID, Double beforeLitre, Double afterLitre,
			Double litreRate, Double beforeNumber, Double afterNumber,
			Double numberRate, Double beforeAvgLitre, Double afterAvgLitre,
			Double avgLitreRate) {
		super();
		this.stationID = stationID;
		this.beforeLitre = beforeLitre;
		this.afterLitre = afterLitre;
		this.litreRate = litreRate;
		this.beforeNumber = beforeNumber;
		this.afterNumber = afterNumber;
		this.numberRate = numberRate;
		this.beforeAvgLitre = beforeAvgLitre;
		this.afterAvgLitre = afterAvgLitre;
		this.avgLitreRate = avgLitreRate;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	
	
}
