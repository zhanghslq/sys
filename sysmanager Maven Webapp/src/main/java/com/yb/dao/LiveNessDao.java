package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.VipLiveness;

public interface LiveNessDao {
	List<String> queryAllDate();
	List<VipLiveness> queryData();
	VipLiveness queryDataByDate(@Param("month")String month);
}
