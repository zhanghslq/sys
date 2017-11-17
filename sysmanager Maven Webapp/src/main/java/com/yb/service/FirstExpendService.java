package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.FirstExpend;
import com.yb.entity.VipRechargePack;

public interface FirstExpendService {
	List<FirstExpend> queryAllExpend();
	List<FirstExpend> queryAllGap();
	//各阶段会员流失占比
	List<FirstExpend> queryLastDeal();
	public List<VipRechargePack> queryDealMonth(Date start,Date end);
}
