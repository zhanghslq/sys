package com.yb.dao;

import java.util.List;

import com.yb.entity.TagGroup;

public interface TagGroupDao {
	void insert(TagGroup tagGroup);
	void delete(Integer id);
	List<TagGroup> queryAll();
	TagGroup queryById(Integer id);
}
