package com.yb.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Heart;

public interface HeartService {
	List<Heart> queryAll();
	List<Heart> queryByNumber(Integer number,String message);
}
