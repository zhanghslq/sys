package com.yb.dao;

import java.util.List;

import com.yb.entity.VipLiveness;

public interface LiveNessDao {
	List<String> queryAllDate();
	List<VipLiveness> queryData();
}
