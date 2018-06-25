package com.yb.entity;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

public class QueryVipData {
	/*String date,@RequestParam(required=false,value="station[]")String[] station,
	@RequestParam(value="oilName[]",required=false)String[] oils,
	@RequestParam(value="shopName[]",required=false)String[] shops,@RequestParam(value="oilNumber[]",required=false)Integer[] oilNumber,
	Integer page,Integer rows,String area*/
	private String date;
	private List<String> station;
	private List<String> oilName;
	private List<String> shopName;
	private List<Integer>  oilNumber;
	private Integer page;
	private Integer rows;
	private String area;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public QueryVipData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QueryVipData(String date, List<String> station,
			List<String> oilName, List<String> shopName,
			List<Integer> oilNumber, Integer page, Integer rows) {
		super();
		this.date = date;
		this.station = station;
		this.oilName = oilName;
		this.shopName = shopName;
		this.oilNumber = oilNumber;
		this.page = page;
		this.rows = rows;
	}
	public QueryVipData(String date, List<String> station,
			List<String> oilName, List<String> shopName,
			List<Integer> oilNumber, Integer page, Integer rows, String area) {
		super();
		this.date = date;
		this.station = station;
		this.oilName = oilName;
		this.shopName = shopName;
		this.oilNumber = oilNumber;
		this.page = page;
		this.rows = rows;
		this.area = area;
	}
	@Override
	public String toString() {
		return "QueryVipData [date=" + date + ", station=" + station
				+ ", oilName=" + oilName + ", shopName=" + shopName
				+ ", oilNumber=" + oilNumber + ", page=" + page + ", rows="
				+ rows + ", area=" + area + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getStation() {
		return station;
	}
	public void setStation(List<String> station) {
		this.station = station;
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
	public List<Integer> getOilNumber() {
		return oilNumber;
	}
	public void setOilNumber(List<Integer> oilNumber) {
		this.oilNumber = oilNumber;
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
