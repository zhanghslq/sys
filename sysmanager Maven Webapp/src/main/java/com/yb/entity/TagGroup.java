package com.yb.entity;


public class TagGroup {
	private Integer id;
	private String loyalty;
	private String identity;
	private String gender;
	private String age;
	private String type;
	private String coupon;
	private String recentOil;
	private String recentNotOil;
	private String shortOil;
	private String mopType;
	private String oilName;
	private String shopName;
	private String station;
	private String groupName;
	private String active;
	private String manyStation;
	private String rfm;
	private String area;
	public TagGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TagGroup{" +
				"id=" + id +
				", loyalty='" + loyalty + '\'' +
				", identity='" + identity + '\'' +
				", gender='" + gender + '\'' +
				", age='" + age + '\'' +
				", type='" + type + '\'' +
				", coupon='" + coupon + '\'' +
				", recentOil='" + recentOil + '\'' +
				", recentNotOil='" + recentNotOil + '\'' +
				", shortOil='" + shortOil + '\'' +
				", mopType='" + mopType + '\'' +
				", oilName='" + oilName + '\'' +
				", shopName='" + shopName + '\'' +
				", station='" + station + '\'' +
				", groupName='" + groupName + '\'' +
				", active='" + active + '\'' +
				", manyStation='" + manyStation + '\'' +
				", rfm='" + rfm + '\'' +
				", area='" + area + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getRecentOil() {
		return recentOil;
	}

	public void setRecentOil(String recentOil) {
		this.recentOil = recentOil;
	}

	public String getRecentNotOil() {
		return recentNotOil;
	}

	public void setRecentNotOil(String recentNotOil) {
		this.recentNotOil = recentNotOil;
	}

	public String getShortOil() {
		return shortOil;
	}

	public void setShortOil(String shortOil) {
		this.shortOil = shortOil;
	}

	public String getMopType() {
		return mopType;
	}

	public void setMopType(String mopType) {
		this.mopType = mopType;
	}

	public String getOilName() {
		return oilName;
	}

	public void setOilName(String oilName) {
		this.oilName = oilName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getManyStation() {
		return manyStation;
	}

	public void setManyStation(String manyStation) {
		this.manyStation = manyStation;
	}

	public String getRfm() {
		return rfm;
	}

	public void setRfm(String rfm) {
		this.rfm = rfm;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public TagGroup(Integer id, String loyalty, String identity, String gender, String age, String type, String coupon, String recentOil, String recentNotOil, String shortOil, String mopType, String oilName, String shopName, String station, String groupName, String active, String manyStation, String rfm, String area) {

		this.id = id;
		this.loyalty = loyalty;
		this.identity = identity;
		this.gender = gender;
		this.age = age;
		this.type = type;
		this.coupon = coupon;
		this.recentOil = recentOil;
		this.recentNotOil = recentNotOil;
		this.shortOil = shortOil;
		this.mopType = mopType;
		this.oilName = oilName;
		this.shopName = shopName;
		this.station = station;
		this.groupName = groupName;
		this.active = active;
		this.manyStation = manyStation;
		this.rfm = rfm;
		this.area = area;
	}
}
