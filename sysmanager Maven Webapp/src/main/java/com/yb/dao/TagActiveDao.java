package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.TagActive;
import com.yb.entity.VipTag;

public interface TagActiveDao {
	void insertTag(TagActive tagActive);//添加活动标签
	void insertVipTag(@Param("list")List<VipTag> list,@Param("tagId")Integer id);//添加活动标签对应的会员
	void delete(Integer id);//删除活动标签
	void deleteVipTag(Integer id);//删除活动标签对应的会员
	List<TagActive> queryAll();
	List<String> queryAllVipTag(List<Integer> list);
}
