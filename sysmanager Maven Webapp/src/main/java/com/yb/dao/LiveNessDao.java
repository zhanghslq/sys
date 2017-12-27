package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.VipLiveness;

public interface LiveNessDao {
	List<String> queryAllDate(@Param("area")String area);
	List<VipLiveness> queryData(@Param("area")String area);
	VipLiveness queryDataByDate(@Param("month")String month,@Param("area")String area);
}
