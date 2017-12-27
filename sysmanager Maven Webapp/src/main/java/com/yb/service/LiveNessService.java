package com.yb.service;

import java.util.List;

import com.yb.entity.VipLiveness;

public interface LiveNessService {
	List<String> queryAllDate(String area);
	List<VipLiveness> queryData(String area);
	VipLiveness queryDataByDate(String month,String area);
}
