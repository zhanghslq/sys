package com.yb.service;

import java.util.List;

import com.yb.entity.Heart;

public interface HeartService {
	List<Heart> queryAll();
	List<Heart> queryByNumber(Integer number,String message,List<String> ids);
}
