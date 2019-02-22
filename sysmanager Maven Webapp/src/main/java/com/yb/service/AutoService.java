package com.yb.service;

public interface AutoService {
	void autoChengdePrice();
	void autoBeijingPrice();
	void autoWeather();

	/**
	 * 每日优惠券自动发送
	 */
	void autoSendCoupon();

	/**
	 * 发送每日简报，油品总额，分类的，还有商品销售额
	 */
	void autoSendDailyData();
}
