package com.yb.service;

import java.util.List;

import com.yb.entity.FirstExpend;

public interface FirstExpendService {
	List<FirstExpend> queryAllExpend();
	List<FirstExpend> queryAllGap();
	//各阶段会员流失占比
	List<FirstExpend> queryLastDeal();
}
