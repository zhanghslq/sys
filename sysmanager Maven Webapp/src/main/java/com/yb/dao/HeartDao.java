package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Heart;

public interface HeartDao {
	List<Heart> queryAll();
	List<Heart> queryByNumber(@Param("number")Integer number,@Param("message")String message,@Param("ids")List<String> ids);
}
