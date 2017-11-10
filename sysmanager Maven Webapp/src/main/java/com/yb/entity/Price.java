package com.yb.entity;

import java.util.Date;
/**
 * 承德的油价
 * @author Administrator
 *
 */
public class Price {
	private Integer id;
	private Double oil90;
	private Double oil93;
	private Double oil97;
	private Double oil0;
	private String city;
	private Date time;
	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Price(Integer id, Double oil90, Double oil93, Double oil97,
			Double oil0, String city, Date time) {
		super();
		this.id = id;
		this.oil90 = oil90;
		this.oil93 = oil93;
		this.oil97 = oil97;
		this.oil0 = oil0;
		this.city = city;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Price [id=" + id + ", oil90=" + oil90 + ", oil93=" + oil93
				+ ", oil97=" + oil97 + ", oil0=" + oil0 + ", city=" + city
				+ ", time=" + time + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getOil90() {
		return oil90;
	}
	public void setOil90(Double oil90) {
		this.oil90 = oil90;
	}
	public Double getOil93() {
		return oil93;
	}
	public void setOil93(Double oil93) {
		this.oil93 = oil93;
	}
	public Double getOil97() {
		return oil97;
	}
	public void setOil97(Double oil97) {
		this.oil97 = oil97;
	}
	public Double getOil0() {
		return oil0;
	}
	public void setOil0(Double oil0) {
		this.oil0 = oil0;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
