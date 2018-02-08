package com.yb.entity;

public class WechatmallStatus {
	private String days;
	private String station_id;
	private Integer refunded_number;
	private Integer refunded_point;
	private Integer notpay_number;
	private Integer notpay_point;
	private Integer tosend_number;
	private Integer tosend_point;
	private Integer paid_number;
	private Integer paid_point;
	private Integer cancel_number;
	private Integer cancel_point;
	public WechatmallStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WechatmallStatus(String days, String station_id,
			Integer refunded_number, Integer refunded_point,
			Integer notpay_number, Integer notpay_point, Integer tosend_number,
			Integer tosend_point, Integer paid_number, Integer paid_point,
			Integer cancel_number, Integer cancel_point) {
		super();
		this.days = days;
		this.station_id = station_id;
		this.refunded_number = refunded_number;
		this.refunded_point = refunded_point;
		this.notpay_number = notpay_number;
		this.notpay_point = notpay_point;
		this.tosend_number = tosend_number;
		this.tosend_point = tosend_point;
		this.paid_number = paid_number;
		this.paid_point = paid_point;
		this.cancel_number = cancel_number;
		this.cancel_point = cancel_point;
	}
	@Override
	public String toString() {
		return "WechatmallStatus [days=" + days + ", station_id=" + station_id
				+ ", refunded_number=" + refunded_number + ", refunded_point="
				+ refunded_point + ", notpay_number=" + notpay_number
				+ ", notpay_point=" + notpay_point + ", tosend_number="
				+ tosend_number + ", tosend_point=" + tosend_point
				+ ", paid_number=" + paid_number + ", paid_point=" + paid_point
				+ ", cancel_number=" + cancel_number + ", cancel_point="
				+ cancel_point + "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getStation_id() {
		return station_id;
	}
	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}
	public Integer getRefunded_number() {
		return refunded_number;
	}
	public void setRefunded_number(Integer refunded_number) {
		this.refunded_number = refunded_number;
	}
	public Integer getRefunded_point() {
		return refunded_point;
	}
	public void setRefunded_point(Integer refunded_point) {
		this.refunded_point = refunded_point;
	}
	public Integer getNotpay_number() {
		return notpay_number;
	}
	public void setNotpay_number(Integer notpay_number) {
		this.notpay_number = notpay_number;
	}
	public Integer getNotpay_point() {
		return notpay_point;
	}
	public void setNotpay_point(Integer notpay_point) {
		this.notpay_point = notpay_point;
	}
	public Integer getTosend_number() {
		return tosend_number;
	}
	public void setTosend_number(Integer tosend_number) {
		this.tosend_number = tosend_number;
	}
	public Integer getTosend_point() {
		return tosend_point;
	}
	public void setTosend_point(Integer tosend_point) {
		this.tosend_point = tosend_point;
	}
	public Integer getPaid_number() {
		return paid_number;
	}
	public void setPaid_number(Integer paid_number) {
		this.paid_number = paid_number;
	}
	public Integer getPaid_point() {
		return paid_point;
	}
	public void setPaid_point(Integer paid_point) {
		this.paid_point = paid_point;
	}
	public Integer getCancel_number() {
		return cancel_number;
	}
	public void setCancel_number(Integer cancel_number) {
		this.cancel_number = cancel_number;
	}
	public Integer getCancel_point() {
		return cancel_point;
	}
	public void setCancel_point(Integer cancel_point) {
		this.cancel_point = cancel_point;
	}
	
	
	
}
