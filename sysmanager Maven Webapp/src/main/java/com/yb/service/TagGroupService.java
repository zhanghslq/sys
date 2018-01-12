package com.yb.service;

import java.util.List;

import com.yb.entity.TagGroup;

public interface TagGroupService {
	void insert(TagGroup tagGroup);
	void delete(Integer id);
	List<TagGroup> queryAll();
	TagGroup queryById(Integer id);
}
