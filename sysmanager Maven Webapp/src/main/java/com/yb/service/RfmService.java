package com.yb.service;

import java.util.List;

import com.yb.entity.DataPack;
import com.yb.entity.DouRfm;

public interface RfmService {
	List<DataPack> queryByGroup(String area);
	List<DouRfm> queryThreeRfms(String area);
	List<DouRfm> queryHistoryRfms(String area);
}
