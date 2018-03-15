package com.yb.service;

import java.util.List;

import com.yb.entity.TagActive;
import com.yb.entity.VipTag;

public interface TagActiveService {
	void insertTag(TagActive tagActive);//添加活动标签
	void insertVipTag(List<VipTag> list,Integer id);//添加活动标签对应的会员
	void delete(Integer id);//删除活动标签
	List<TagActive> queryAll();
	List<TagActive> queryByArea(String area);
	List <String> queryAllVipTag(List<Integer> list);
}
