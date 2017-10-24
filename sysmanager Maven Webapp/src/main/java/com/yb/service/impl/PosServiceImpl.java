package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.PosDao;
import com.yb.entity.Pos;
import com.yb.service.PosService;

@Service
public class PosServiceImpl implements PosService{

	@Autowired
	private PosDao posDao;
	@Override
	public List<Pos> queryAll() {
		// TODO Auto-generated method stub
		List<Pos> list = posDao.queryAll();
		return list;
	}

	@Override
	public Pos queryById(Integer id) {
		// TODO Auto-generated method stub
		 Pos pos = posDao.queryById(id);
		return pos;
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		posDao.delete(id);
	}

	@Override
	@Transactional
	public void add(String name) {
		// TODO Auto-generated method stub
		posDao.add(name);
	}

	@Override
	@Transactional
	public void update(Pos pos) {
		// TODO Auto-generated method stub
		posDao.update(pos);
	}

}
