package com.yb.entity;

public class BasketRules {
	private String row_names;
	private String lhs;
	private String var3;
	private String rhs;
	private Double support;
	private Double confidence;
	private Double lift;
	private Double count;
	public BasketRules() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BasketRules(String row_names, String lhs, String var3, String rhs,
			Double support, Double confidence, Double lift, Double count) {
		super();
		this.row_names = row_names;
		this.lhs = lhs;
		this.var3 = var3;
		this.rhs = rhs;
		this.support = support;
		this.confidence = confidence;
		this.lift = lift;
		this.count = count;
	}
	@Override
	public String toString() {
		return "BasketRules [row_names=" + row_names + ", lhs=" + lhs
				+ ", var3=" + var3 + ", rhs=" + rhs + ", support=" + support
				+ ", confidence=" + confidence + ", lift=" + lift + ", count="
				+ count + "]";
	}
	public String getRow_names() {
		return row_names;
	}
	public void setRow_names(String row_names) {
		this.row_names = row_names;
	}
	public String getLhs() {
		return lhs;
	}
	public void setLhs(String lhs) {
		this.lhs = lhs;
	}
	public String getVar3() {
		return var3;
	}
	public void setVar3(String var3) {
		this.var3 = var3;
	}
	public String getRhs() {
		return rhs;
	}
	public void setRhs(String rhs) {
		this.rhs = rhs;
	}
	public Double getSupport() {
		return support;
	}
	public void setSupport(Double support) {
		this.support = support;
	}
	public Double getConfidence() {
		return confidence;
	}
	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}
	public Double getLift() {
		return lift;
	}
	public void setLift(Double lift) {
		this.lift = lift;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	
}
