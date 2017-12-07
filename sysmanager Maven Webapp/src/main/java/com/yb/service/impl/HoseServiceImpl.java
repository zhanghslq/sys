package com.yb.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.HoseDao;
import com.yb.service.HoseService;

@Service
public class HoseServiceImpl implements HoseService{

	@Autowired
	private HoseDao hoseDao;
	@Override
	public Double queryHoseByPumpAndDate(Date start, Date end, String pump,
			Integer hose,String station) {
		// TODO Auto-generated method stub
		Double queryHoseByPumpAndDate = hoseDao.queryHoseByPumpAndDate(start, end, pump, hose,station);
		
		return queryHoseByPumpAndDate;
	}

}
