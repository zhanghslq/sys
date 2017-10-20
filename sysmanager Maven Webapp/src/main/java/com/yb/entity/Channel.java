package com.yb.entity;

public class Channel {
	private Integer unknown;
	private Integer pc;
	private Integer mobile;
	private Integer wechat;
	private Integer aplipay;
	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Channel(Integer unknown, Integer pc, Integer mobile, Integer wechat,
			Integer aplipay) {
		super();
		this.unknown = unknown;
		this.pc = pc;
		this.mobile = mobile;
		this.wechat = wechat;
		this.aplipay = aplipay;
	}
	@Override
	public String toString() {
		return "Channel [unknown=" + unknown + ", pc=" + pc + ", mobile="
				+ mobile + ", wechat=" + wechat + ", aplipay=" + aplipay + "]";
	}
	public Integer getUnknown() {
		return unknown;
	}
	public void setUnknown(Integer unknown) {
		this.unknown = unknown;
	}
	public Integer getPc() {
		return pc;
	}
	public void setPc(Integer pc) {
		this.pc = pc;
	}
	public Integer getMobile() {
		return mobile;
	}
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	public Integer getWechat() {
		return wechat;
	}
	public void setWechat(Integer wechat) {
		this.wechat = wechat;
	}
	public Integer getAplipay() {
		return aplipay;
	}
	public void setAplipay(Integer aplipay) {
		this.aplipay = aplipay;
	}
	
}
