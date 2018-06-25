package com.yb.entity;

import java.util.List;

public class DataVip {
	private List<String> loyalty;
	private List<String> identity;
	private List<String> gender;
	private List<String> age;
	private List<String> type;
	private List<String> coupon;
	private List<String> recentOil;
	private List<String> recentNotOil;
	private List<String> shortOil;
	private List<String> mopType;
	private List<String> oilName;
	private List<String> shopName;
	private List<String> station;
	private List<String> tagActive;
	private List<String> manyStation;
	private String area;
	private Integer page;
	private Integer rows;
	public DataVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DataVip(List<String> loyalty, List<String> identity,
			List<String> gender, List<String> age, List<String> type,
			List<String> coupon, List<String> recentOil,
			List<String> recentNotOil, List<String> shortOil,
			List<String> mopType, List<String> oilName, List<String> shopName,
			List<String> station, List<String> tagActive,
			List<String> manyStation, String area, Integer page, Integer rows) {
		super();
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
		this.tagActive = tagActive;
		this.manyStation = manyStation;
		this.area = area;
		this.page = page;
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "DataVip [loyalty=" + loyalty + ", identity=" + identity
				+ ", gender=" + gender + ", age=" + age + ", type=" + type
				+ ", coupon=" + coupon + ", recentOil=" + recentOil
				+ ", recentNotOil=" + recentNotOil + ", shortOil=" + shortOil
				+ ", mopType=" + mopType + ", oilName=" + oilName
				+ ", shopName=" + shopName + ", station=" + station
				+ ", tagActive=" + tagActive + ", manyStation=" + manyStation
				+ ", area=" + area + ", page=" + page + ", rows=" + rows + "]";
	}
	public List<String> getLoyalty() {
		return loyalty;
	}
	public void setLoyalty(List<String> loyalty) {
		this.loyalty = loyalty;
	}
	public List<String> getIdentity() {
		return identity;
	}
	public void setIdentity(List<String> identity) {
		this.identity = identity;
	}
	public List<String> getGender() {
		return gender;
	}
	public void setGender(List<String> gender) {
		this.gender = gender;
	}
	public List<String> getAge() {
		return age;
	}
	public void setAge(List<String> age) {
		this.age = age;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public List<String> getCoupon() {
		return coupon;
	}
	public void setCoupon(List<String> coupon) {
		this.coupon = coupon;
	}
	public List<String> getRecentOil() {
		return recentOil;
	}
	public void setRecentOil(List<String> recentOil) {
		this.recentOil = recentOil;
	}
	public List<String> getRecentNotOil() {
		return recentNotOil;
	}
	public void setRecentNotOil(List<String> recentNotOil) {
		this.recentNotOil = recentNotOil;
	}
	public List<String> getShortOil() {
		return shortOil;
	}
	public void setShortOil(List<String> shortOil) {
		this.shortOil = shortOil;
	}
	public List<String> getMopType() {
		return mopType;
	}
	public void setMopType(List<String> mopType) {
		this.mopType = mopType;
	}
	public List<String> getOilName() {
		return oilName;
	}
	public void setOilName(List<String> oilName) {
		this.oilName = oilName;
	}
	public List<String> getShopName() {
		return shopName;
	}
	public void setShopName(List<String> shopName) {
		this.shopName = shopName;
	}
	public List<String> getStation() {
		return station;
	}
	public void setStation(List<String> station) {
		this.station = station;
	}
	public List<String> getTagActive() {
		return tagActive;
	}
	public void setTagActive(List<String> tagActive) {
		this.tagActive = tagActive;
	}
	public List<String> getManyStation() {
		return manyStation;
	}
	public void setManyStation(List<String> manyStation) {
		this.manyStation = manyStation;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
