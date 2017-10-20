package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.StationDao;
import com.yb.dao.TagDao;
import com.yb.entity.Tag;
import com.yb.service.TagService;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagDao tagDao;
	@Autowired
	private StationDao stationDao;
	@Transactional
	@Override
	public void add(Tag tag) {
		// TODO Auto-generated method stub
		tagDao.add(tag);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tagDao.delete(id);
		tagDao.deleteFromst(id);
	}

	@Override
	public List<Tag> queryAll() {
		// TODO Auto-generated method stub
		List<Tag> list = tagDao.queryAll();
		return list;
	}

	@Override
	public Tag queryById(Integer id) {
		// TODO Auto-generated method stub
		Tag tag = tagDao.queryById(id);
		return tag;
	}

	@Override
	@Transactional
	public void update(Tag tag) {
		// TODO Auto-generated method stub
		tagDao.update(tag);
	}

}
