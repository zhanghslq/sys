package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.VipRechargeMonth;
import com.yb.entity.VipRechargePack;

public interface VipRechargeMonthDao {
	List<VipRechargeMonth> query(@Param("start")Date start,@Param("end")Date end);//这个可以做会员充值的其他部分
	List<VipRechargePack> querySingle(@Param("start")Date start,@Param("end")Date end);//为散点图取出来的数据
}