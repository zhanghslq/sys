package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.CategoryDao;
import com.yb.dao.StationDao;
import com.yb.entity.Category;
import com.yb.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private StationDao stationDao;
	@Override
	@Transactional
	public void add(Category category) {
		// TODO Auto-generated method stub
		categoryDao.add(category);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		stationDao.setCategoryNull(id);
		categoryDao.delete(id);
		
	}

	@Override
	public Category queryById(Integer id) {
		// TODO Auto-generated method stub
		Category category = categoryDao.queryById(id);
		return category;
	}

	@Override
	public List<Category> queryAll() {
		// TODO Auto-generated method stub
		List<Category> list = categoryDao.queryAll();
		return list;
	}

	@Transactional
	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

}
