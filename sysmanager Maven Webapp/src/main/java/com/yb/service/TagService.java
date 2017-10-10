package com.yb.service;

import java.util.List;

import com.yb.entity.Tag;

public interface TagService {
	void add(Tag tag);
	void delete(Integer id);
	void update(Tag tag);
	List<Tag> queryAll();
	Tag queryById(Integer id);
}
