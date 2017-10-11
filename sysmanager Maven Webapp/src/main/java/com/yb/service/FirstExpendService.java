package com.yb.service;

import java.util.List;

import com.yb.entity.FirstExpend;

public interface FirstExpendService {
	List<FirstExpend> queryAllExpend();
	List<FirstExpend> queryAllGap();
}
