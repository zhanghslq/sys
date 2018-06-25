package com.yb.entity;

public class VipTag {
	private String carduser_id;
	private String name;
	private String mobilePhone;
	private String tag;
	public VipTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipTag(String carduser_id, String name, String mobilePhone,
			String tag) {
		super();
		this.carduser_id = carduser_id;
		this.name = name;
		this.mobilePhone = mobilePhone;
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "VipTag [carduser_id=" + carduser_id + ", name=" + name
				+ ", mobilePhone=" + mobilePhone + ", tag=" + tag + "]";
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
