package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Tag;

public interface TagDao {
	public void add(Tag tag);
	void delete(Integer id);
	void update(Tag tag);
	Tag queryById(Integer id);
	List<Tag> queryAll();
//	这是联合查询的标签，先放置在标签的dao里面
	List<Tag> queryByStationId(String id);
	//根据油站id删除中间表中的数据
	void deleteByStationId(String id);
	//中间表添加数据
	void addTagStation(@Param("stationId")String stationId,@Param("tagId")Integer tagId);
	//查询所有
	
	void deleteFromst(Integer id);
}
