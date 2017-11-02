package com.yb.dao;

import java.util.List;

import com.yb.entity.FirstExpend;

public interface FirstExpendDao {
	public List<FirstExpend> queryAllExpend();//注册到首次消费
	public List<FirstExpend> queryAllGap();//两次消费间隔
	public List<FirstExpend> queryLastDeal();
}
