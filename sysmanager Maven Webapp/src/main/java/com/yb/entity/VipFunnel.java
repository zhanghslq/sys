package com.yb.entity;

public class VipFunnel {
	private String month;
	private Integer sum;
	private Integer atLeastOne;
	private Integer noDeal;
	private Integer liveness;
	public VipFunnel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipFunnel(String month, Integer sum, Integer atLeastOne,
			Integer noDeal, Integer liveness) {
		super();
		this.month = month;
		this.sum = sum;
		this.atLeastOne = atLeastOne;
		this.noDeal = noDeal;
		this.liveness = liveness;
	}
	@Override
	public String toString() {
		return "VipFunnel [month=" + month + ", sum=" + sum + ", atLeastOne="
				+ atLeastOne + ", noDeal=" + noDeal + ", liveness=" + liveness
				+ "]";
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Integer getAtLeastOne() {
		return atLeastOne;
	}
	public void setAtLeastOne(Integer atLeastOne) {
		this.atLeastOne = atLeastOne;
	}
	public Integer getNoDeal() {
		return noDeal;
	}
	public void setNoDeal(Integer noDeal) {
		this.noDeal = noDeal;
	}
	public Integer getLiveness() {
		return liveness;
	}
	public void setLiveness(Integer liveness) {
		this.liveness = liveness;
	}
	
}
