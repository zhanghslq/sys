package com.yb.entity;

public class Mop {
	private String days;
	private Double EPSMoney;//EPS会员
	private Double couponMoney;//优惠券
	private Double vipCouponMoney;//会员优惠券
	private Double creditCardMoney;//信用卡
	private Double teamCardMoney;//壳牌车队卡
	private Double wechatMoney;//微信支付
	private Double alipayMoney;//支付宝支付
	private Double chequeMoney;//支票支付
	private Double didiMoney;//滴滴支付
	private Double cashMoney;//现金
	private Double EPaymentMoney;//电子支付优惠
	private Double baiduMoney;//百度支付
	private Double thirdPaymentMoney;//第三方卡
	private Double carInMoney;//车到收款
	private Double unionpayCouponMoney;//银联钱包优惠券
	public Mop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mop(String days, Double ePSMoney, Double couponMoney,
			Double vipCouponMoney, Double creditCardMoney,
			Double teamCardMoney, Double wechatMoney, Double alipayMoney,
			Double chequeMoney, Double didiMoney, Double cashMoney,
			Double ePaymentMoney, Double baiduMoney, Double thirdPaymentMoney,
			Double carInMoney, Double unionpayCouponMoney) {
		super();
		this.days = days;
		EPSMoney = ePSMoney;
		this.couponMoney = couponMoney;
		this.vipCouponMoney = vipCouponMoney;
		this.creditCardMoney = creditCardMoney;
		this.teamCardMoney = teamCardMoney;
		this.wechatMoney = wechatMoney;
		this.alipayMoney = alipayMoney;
		this.chequeMoney = chequeMoney;
		this.didiMoney = didiMoney;
		this.cashMoney = cashMoney;
		EPaymentMoney = ePaymentMoney;
		this.baiduMoney = baiduMoney;
		this.thirdPaymentMoney = thirdPaymentMoney;
		this.carInMoney = carInMoney;
		this.unionpayCouponMoney = unionpayCouponMoney;
	}
	@Override
	public String toString() {
		return "Mop [days=" + days + ", EPSMoney=" + EPSMoney
				+ ", couponMoney=" + couponMoney + ", vipCouponMoney="
				+ vipCouponMoney + ", creditCardMoney=" + creditCardMoney
				+ ", teamCardMoney=" + teamCardMoney + ", wechatMoney="
				+ wechatMoney + ", alipayMoney=" + alipayMoney
				+ ", chequeMoney=" + chequeMoney + ", didiMoney=" + didiMoney
				+ ", cashMoney=" + cashMoney + ", EPaymentMoney="
				+ EPaymentMoney + ", baiduMoney=" + baiduMoney
				+ ", thirdPaymentMoney=" + thirdPaymentMoney + ", carInMoney="
				+ carInMoney + ", unionpayCouponMoney=" + unionpayCouponMoney
				+ "]";
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public Double getEPSMoney() {
		return EPSMoney;
	}
	public void setEPSMoney(Double ePSMoney) {
		EPSMoney = ePSMoney;
	}
	public Double getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}
	public Double getVipCouponMoney() {
		return vipCouponMoney;
	}
	public void setVipCouponMoney(Double vipCouponMoney) {
		this.vipCouponMoney = vipCouponMoney;
	}
	public Double getCreditCardMoney() {
		return creditCardMoney;
	}
	public void setCreditCardMoney(Double creditCardMoney) {
		this.creditCardMoney = creditCardMoney;
	}
	public Double getTeamCardMoney() {
		return teamCardMoney;
	}
	public void setTeamCardMoney(Double teamCardMoney) {
		this.teamCardMoney = teamCardMoney;
	}
	public Double getWechatMoney() {
		return wechatMoney;
	}
	public void setWechatMoney(Double wechatMoney) {
		this.wechatMoney = wechatMoney;
	}
	public Double getAlipayMoney() {
		return alipayMoney;
	}
	public void setAlipayMoney(Double alipayMoney) {
		this.alipayMoney = alipayMoney;
	}
	public Double getChequeMoney() {
		return chequeMoney;
	}
	public void setChequeMoney(Double chequeMoney) {
		this.chequeMoney = chequeMoney;
	}
	public Double getDidiMoney() {
		return didiMoney;
	}
	public void setDidiMoney(Double didiMoney) {
		this.didiMoney = didiMoney;
	}
	public Double getCashMoney() {
		return cashMoney;
	}
	public void setCashMoney(Double cashMoney) {
		this.cashMoney = cashMoney;
	}
	public Double getEPaymentMoney() {
		return EPaymentMoney;
	}
	public void setEPaymentMoney(Double ePaymentMoney) {
		EPaymentMoney = ePaymentMoney;
	}
	public Double getBaiduMoney() {
		return baiduMoney;
	}
	public void setBaiduMoney(Double baiduMoney) {
		this.baiduMoney = baiduMoney;
	}
	public Double getThirdPaymentMoney() {
		return thirdPaymentMoney;
	}
	public void setThirdPaymentMoney(Double thirdPaymentMoney) {
		this.thirdPaymentMoney = thirdPaymentMoney;
	}
	public Double getCarInMoney() {
		return carInMoney;
	}
	public void setCarInMoney(Double carInMoney) {
		this.carInMoney = carInMoney;
	}
	public Double getUnionpayCouponMoney() {
		return unionpayCouponMoney;
	}
	public void setUnionpayCouponMoney(Double unionpayCouponMoney) {
		this.unionpayCouponMoney = unionpayCouponMoney;
	}
	
}
