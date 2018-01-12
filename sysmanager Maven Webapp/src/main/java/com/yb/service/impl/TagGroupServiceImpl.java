package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.TagGroupDao;
import com.yb.entity.TagGroup;
import com.yb.service.TagGroupService;

@Service
@Transactional
public class TagGroupServiceImpl implements TagGroupService{

	@Autowired
	private TagGroupDao tagGroupDao;
	@Override
	public void insert(TagGroup tagGroup) {
		// TODO Auto-generated method stub
		tagGroupDao.insert(tagGroup);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tagGroupDao.delete(id);
	}

	@Override
	public List<TagGroup> queryAll() {
		// TODO Auto-generated method stub
		List<TagGroup> list = tagGroupDao.queryAll();
		return list;
	}

	@Override
	public TagGroup queryById(Integer id) {
		// TODO Auto-generated method stub
		TagGroup tagGroup = tagGroupDao.queryById(id);
		return tagGroup;
	}
}
