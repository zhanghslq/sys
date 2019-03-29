package com.yb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付方式
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mop {
	private String days;
	private String stationID;
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
	private Double zebpayMoney;//斑马支付
	private Double microcarMoney;//微车支付
	private Double bankdiscountMoney;//银行异业优惠


	
}
