package com.yb.entity;

public class Tactics {
	private String month;
	private Integer send_tactics_id;
	private String tacticsTitle;
	private Integer allNumber;
	private Integer usedNumber;
	public Tactics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tactics(String month, Integer send_tactics_id, String tacticsTitle,
			Integer allNumber, Integer usedNumber) {
		super();
		this.month = month;
		this.send_tactics_id = send_tactics_id;
		this.tacticsTitle = tacticsTitle;
		this.allNumber = allNumber;
		this.usedNumber = usedNumber;
	}
	@Override
	public String toString() {
		return "Tactics [month=" + month + ", send_tactics_id="
				+ send_tactics_id + ", tacticsTitle=" + tacticsTitle
				+ ", allNumber=" + allNumber + ", usedNumber=" + usedNumber
				+ "]";
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getSend_tactics_id() {
		return send_tactics_id;
	}
	public void setSend_tactics_id(Integer send_tactics_id) {
		this.send_tactics_id = send_tactics_id;
	}
	public String getTacticsTitle() {
		return tacticsTitle;
	}
	public void setTacticsTitle(String tacticsTitle) {
		this.tacticsTitle = tacticsTitle;
	}
	public Integer getAllNumber() {
		return allNumber;
	}
	public void setAllNumber(Integer allNumber) {
		this.allNumber = allNumber;
	}
	public Integer getUsedNumber() {
		return usedNumber;
	}
	public void setUsedNumber(Integer usedNumber) {
		this.usedNumber = usedNumber;
	}
	
}
