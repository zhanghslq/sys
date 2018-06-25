package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.TagActiveDao;
import com.yb.entity.TagActive;
import com.yb.entity.VipTag;
import com.yb.service.TagActiveService;

@Service
@Transactional
public class TagActiveServiceImpl implements TagActiveService{

	@Autowired
	private TagActiveDao tagActiveDao;
	@Override
	public void insertTag(TagActive tagActive) {
		// TODO Auto-generated method stub
		tagActiveDao.insertTag(tagActive);
	}

	@Override
	public void insertVipTag(List<VipTag> list, Integer id) {
		// TODO Auto-generated method stub
		tagActiveDao.insertVipTag(list, id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tagActiveDao.delete(id);
		tagActiveDao.deleteVipTag(id);
	}

	@Override
	public List<TagActive> queryAll() {
		// TODO Auto-generated method stub
		List<TagActive> list = tagActiveDao.queryAll();
		return list;
	}

	@Override
	public List<String> queryAllVipTag(List<Integer> list) {
		// TODO Auto-generated method stub
		List<String> set = tagActiveDao.queryAllVipTag(list);
		return set;
	}

	@Override
	public List<TagActive> queryByArea(String area) {
		// TODO Auto-generated method stub
		List<TagActive> list = tagActiveDao.queryByArea(area);
		return list;
	}
	
}
