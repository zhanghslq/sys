package com.yb.service;

import java.util.Date;
import java.util.List;

import com.yb.entity.FirstExpend;
import com.yb.entity.VipRechargePack;

public interface FirstExpendService {
	List<FirstExpend> queryAllExpend(String area);
	List<FirstExpend> queryAllGap(String area);
	//各阶段会员流失占比
	List<FirstExpend> queryLastDeal(String area);
	public List<VipRechargePack> queryDealMonth(Date start,Date end,String area);
	public List<VipRechargePack> exportDealMonth(Date start,Date end,String area,Integer startNum,Integer size);
	
}
