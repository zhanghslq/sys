package com.yb.dao;

import java.util.List;

import com.yb.entity.Category;

public interface CategoryDao {
	void add(Category category);
	void delete(Integer id);
	void update(Category category);
	Category queryById(Integer id);
	List<Category> queryAll();
}
