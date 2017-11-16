package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.FestivalDao;
import com.yb.entity.Festival;
import com.yb.service.FestivalService;

@Service
@Transactional
public class FestivalServiceImpl implements FestivalService{

	@Autowired
	private FestivalDao festivalDao;
	@Override
	public void add(Festival festival) {
		// TODO Auto-generated method stub
		festivalDao.add(festival);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		festivalDao.delete(id);
	}

	@Override
	public void update(Festival festival) {
		// TODO Auto-generated method stub
		festivalDao.update(festival);
	}

	@Override
	public Festival queryByName(String name) {
		// TODO Auto-generated method stub
		Festival festival = festivalDao.queryByName(name);
		return festival;
	}

	@Override
	public List<Festival> queryAll() {
		// TODO Auto-generated method stub
		List<Festival> list = festivalDao.queryAll();
		return list;
	}

}
