package com.yb.service;

import java.util.Date;
import java.util.List;
import com.yb.entity.Coupon;
import com.yb.entity.DataPack;

public interface CouponService {
	public List<Coupon> query(Date start,Date end);
	public List<DataPack> queryZhanbi();
}
