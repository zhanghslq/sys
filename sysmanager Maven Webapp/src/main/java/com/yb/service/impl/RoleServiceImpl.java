package com.yb.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.dao.RoleDao;
import com.yb.entity.Role;
import com.yb.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Override
	public void insert(Role role) {
		// TODO Auto-generated method stub
		role.setId(UUID.randomUUID().toString());
		roleDao.insert(role);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		roleDao.deleteFromUserRole(id);
		roleDao.delete(id);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		List<Role> list = roleDao.queryAll();
		return list;
	}

	@Override
	public Role queryByUserId(String id) {
		// TODO Auto-generated method stub
		Role role = roleDao.queryByUserId(id);
		return role;
	}

	@Override
	public void insertRolePermission(String rid, String pid) {
		// TODO Auto-generated method stub
		roleDao.insertRolePermission(rid, pid);
	}

	@Override
	public void deleteRolePermission(String rid) {
		// TODO Auto-generated method stub
		roleDao.deleteRolePermission(rid);
	}

	@Override
	public Role queryById(String id) {
		// TODO Auto-generated method stub
		Role role = roleDao.queryById(id);
		return role;
	}

	@Override
	public Role queryByName(String name) {
		// TODO Auto-generated method stub
		Role role = roleDao.queryByName(name);
		return role;
	}

	@Override
	public void updatePermission(String rid, List<String> pid) {
		// TODO Auto-generated method stub
		roleDao.deleteRolePermission(rid);//先删除原来的
		if(pid!=null &&pid.size()!=0){
			for (String string : pid) {
				roleDao.insertRolePermission(rid, string);//循环把权限加上
			}
		}
	}

}
