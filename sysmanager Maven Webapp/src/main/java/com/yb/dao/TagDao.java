package com.yb.dao;

import java.util.List;

import com.yb.entity.Tag;

public interface TagDao {
	public void add(Tag tag);
	void delete(Integer id);
	void update(Tag tag);
	Tag queryById(Integer id);
	List<Tag> queryAll();
}
