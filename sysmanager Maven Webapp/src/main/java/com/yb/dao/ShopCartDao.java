package com.yb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.InterPack;
import com.yb.entity.LongPack;
import com.yb.entity.StringPack;

public interface ShopCartDao {
	List<InterPack> query(@Param("station")List<String> station,@Param("start")Date start,@Param("end")Date end,
			@Param("oil")String oil,@Param("department")String department);
	
	List<StringPack> queryDepartment();
	List<StringPack> queryProduct();
	
	Integer getStatus();
	void updateStatus(@Param("status")Integer status);
}
