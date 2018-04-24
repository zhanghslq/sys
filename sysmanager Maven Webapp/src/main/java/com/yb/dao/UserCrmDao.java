package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.Admin;
import com.yb.entity.PermissionPack;
/**
 * 接口权限管理
 * @author lenovo
 *
 */
public interface UserCrmDao {
	List<Admin> queryAll();
	Admin queryByName(@Param("name")String name);
	void delete(@Param("id")String id);
	void update(@Param("name")String name,@Param("password")String password);
	void insert(@Param("admin")Admin admin);
	//查询所有权限
	
	List<PermissionPack> queryPermission();
	//查询用户拥有的权限
	List<String> queryPermissionByUser(@Param("id")String id);
	void deletePermissionByUserId(@Param("id")String id);
	void insertPermission(@Param("uid")String uid,@Param("pid")List<String> pid);
}
