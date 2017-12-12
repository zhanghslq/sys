package com.yb.entity;

public class VipTag {
	private String carduser_id;
	private String name;
	private String mobilePhone;
	public VipTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipTag(String carduser_id, String name, String mobilePhone) {
		super();
		this.carduser_id = carduser_id;
		this.name = name;
		this.mobilePhone = mobilePhone;
	}
	@Override
	public String toString() {
		return "VipTag [carduser_id=" + carduser_id + ", name=" + name
				+ ", mobilePhone=" + mobilePhone + "]";
	}
	public String getCarduser_id() {
		return carduser_id;
	}
	public void setCarduser_id(String carduser_id) {
		this.carduser_id = carduser_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
}
