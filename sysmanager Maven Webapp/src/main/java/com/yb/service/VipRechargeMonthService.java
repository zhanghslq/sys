package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.VipRechargeMonth;
import com.yb.entity.VipRechargePack;

public interface VipRechargeMonthService {
	List<VipRechargeMonth> query(Date start,Date end);//这个可以做会员充值的其他部分
	List<VipRechargePack> querySingle(Date start,Date end);
	
}
