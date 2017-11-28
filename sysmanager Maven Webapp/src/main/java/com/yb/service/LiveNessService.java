package com.yb.service;

import java.util.List;

import com.yb.entity.VipLiveness;

public interface LiveNessService {
	List<String> queryAllDate();
	List<VipLiveness> queryData();
	VipLiveness queryDataByDate(String month);
}
