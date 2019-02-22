package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Admin;

public interface AdminDao {

	 Admin queryById(String id);
	 Admin queryByName(String name);
	 void insert(Admin admin);
	 void delete(String id);

	/**
	 * 批量查询用户
	 * @param start
	 * @param rows
	 * @return
	 */
	 List<Admin> queryAll(@Param("start")Integer start,@Param("rows") Integer rows);//查询所有用户

	/**
	 * 查询用户总数
	 * @return
	 */
	Integer queryTotal();

	 void update(@Param("name")String name,@Param("password")String password);
	 void updateName(@Param("id")String id,@Param("name")String name);
	
	void deleteFromURByUserId(String id);
	void insertUserRole(@Param("uid")String uid,@Param("rid")String rid);
}
