package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.FirstExpend;
import com.yb.entity.VipRechargePack;

public interface FirstExpendDao {
	public List<FirstExpend> queryAllExpend(@Param("area")String area);//注册到首次消费
	public List<FirstExpend> queryAllGap(@Param("area")String area);//两次消费间隔
	public List<FirstExpend> queryLastDeal(@Param("area")String area);
	public List<VipRechargePack> queryDealMonth(@Param("start") Date start,@Param("end")Date end,@Param("area")String area);
}
