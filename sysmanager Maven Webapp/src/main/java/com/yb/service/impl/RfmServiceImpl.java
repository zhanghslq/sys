package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.RfmDao;
import com.yb.entity.DataPack;
import com.yb.entity.DouRfm;
import com.yb.service.RfmService;

@Service
public class RfmServiceImpl implements RfmService{

	@Autowired
	private RfmDao rfmDao;
	@Override
	public List<DataPack> queryByGroup(String area) {
		// TODO Auto-generated method stub
		List<DataPack> queryByGroup = rfmDao.queryByGroup(area);
		return queryByGroup;
	}
	@Override
	public List<DouRfm> queryThreeRfms(String area) {
		// TODO Auto-generated method stub
		List<DouRfm> list = rfmDao.queryThreeRfms(area);
		return list;
	}
	@Override
	public List<DouRfm> queryHistoryRfms(String area) {
		// TODO Auto-generated method stub
		List<DouRfm> list = rfmDao.queryHistoryRfms(area);
		return list;
	}

}
