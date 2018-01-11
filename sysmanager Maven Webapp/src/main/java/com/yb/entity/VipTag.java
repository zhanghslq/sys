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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carduser_id == null) ? 0 : carduser_id.hashCode());
		result = prime * result
				+ ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VipTag other = (VipTag) obj;
		if (carduser_id == null) {
			if (other.carduser_id != null)
				return false;
		} else if (!carduser_id.equals(other.carduser_id))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
