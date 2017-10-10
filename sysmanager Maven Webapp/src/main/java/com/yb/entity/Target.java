package com.yb.entity;

import java.io.Serializable;
import java.util.Date;

public class Target implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date month;
	private String oilStation;
	private Double realOilAmount;
	private Double realGasolineAmount;
	private Double realDieselAmount;
	private Double realStoreAmount;
	private Double oilTargetAmount;
	private Double targetGasolineAmount;
	private Double targetDieselAmount;
	private Double targetStoreAmount;
	public Target() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Target(Integer id, Date month, String oilStation,
			Double realOilAmount, Double realGasolineAmount,
			Double realDieselAmount, Double realStoreAmount,
			Double oilTargetAmount, Double targetGasolineAmount,
			Double targetDieselAmount, Double targetStoreAmount) {
		super();
		this.id = id;
		this.month = month;
		this.oilStation = oilStation;
		this.realOilAmount = realOilAmount;
		this.realGasolineAmount = realGasolineAmount;
		this.realDieselAmount = realDieselAmount;
		this.realStoreAmount = realStoreAmount;
		this.oilTargetAmount = oilTargetAmount;
		this.targetGasolineAmount = targetGasolineAmount;
		this.targetDieselAmount = targetDieselAmount;
		this.targetStoreAmount = targetStoreAmount;
	}
	@Override
	public String toString() {
		return "Target [id=" + id + ", month=" + month + ", oilStation="
				+ oilStation + ", realOilAmount=" + realOilAmount
				+ ", realGasolineAmount=" + realGasolineAmount
				+ ", realDieselAmount=" + realDieselAmount
				+ ", realStoreAmount=" + realStoreAmount + ", oilTargetAmount="
				+ oilTargetAmount + ", targetGasolineAmount="
				+ targetGasolineAmount + ", targetDieselAmount="
				+ targetDieselAmount + ", targetStoreAmount="
				+ targetStoreAmount + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public String getOilStation() {
		return oilStation;
	}
	public void setOilStation(String oilStation) {
		this.oilStation = oilStation;
	}
	public Double getRealOilAmount() {
		return realOilAmount;
	}
	public void setRealOilAmount(Double realOilAmount) {
		this.realOilAmount = realOilAmount;
	}
	public Double getRealGasolineAmount() {
		return realGasolineAmount;
	}
	public void setRealGasolineAmount(Double realGasolineAmount) {
		this.realGasolineAmount = realGasolineAmount;
	}
	public Double getRealDieselAmount() {
		return realDieselAmount;
	}
	public void setRealDieselAmount(Double realDieselAmount) {
		this.realDieselAmount = realDieselAmount;
	}
	public Double getRealStoreAmount() {
		return realStoreAmount;
	}
	public void setRealStoreAmount(Double realStoreAmount) {
		this.realStoreAmount = realStoreAmount;
	}
	public Double getOilTargetAmount() {
		return oilTargetAmount;
	}
	public void setOilTargetAmount(Double oilTargetAmount) {
		this.oilTargetAmount = oilTargetAmount;
	}
	public Double getTargetGasolineAmount() {
		return targetGasolineAmount;
	}
	public void setTargetGasolineAmount(Double targetGasolineAmount) {
		this.targetGasolineAmount = targetGasolineAmount;
	}
	public Double getTargetDieselAmount() {
		return targetDieselAmount;
	}
	public void setTargetDieselAmount(Double targetDieselAmount) {
		this.targetDieselAmount = targetDieselAmount;
	}
	public Double getTargetStoreAmount() {
		return targetStoreAmount;
	}
	public void setTargetStoreAmount(Double targetStoreAmount) {
		this.targetStoreAmount = targetStoreAmount;
	}
	
}
