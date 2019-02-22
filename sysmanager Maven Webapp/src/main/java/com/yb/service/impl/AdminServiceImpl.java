package com.yb.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.test.MD5Utils;
import com.baizhi.test.Saltutils;
import com.yb.dao.AdminDao;
import com.yb.dao.StationDao;
import com.yb.entity.Admin;
import com.yb.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private StationDao stationDao;
	@Override
	public Admin queryByName(String name) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.queryByName(name);
		return admin;
	}
	
	@Override
	public void regist(Admin admin) {
		// TODO Auto-generated method stub
	    admin.setId(UUID.randomUUID().toString());
        String password = admin.getPassword();
        String salt = Saltutils.getSalt(6);
        admin.setSalt(salt);
        String digest = MD5Utils.getDigest(salt+password);
        admin.setPassword(digest);
        adminDao.insert(admin);
	}

	

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
		Admin admin = adminDao.queryById(id);
		stationDao.deleteByUserId(admin.getName());
		adminDao.deleteFromURByUserId(id);
		
		adminDao.delete(id);
	}

	@Override
	public void update(String name, String password) {
		// TODO Auto-generated method stub
		adminDao.update(name, password);
	}

	@Override
	public List<Admin> queryAll(Integer start,Integer rows) {
		// TODO Auto-generated method stub
		List<Admin> queryAll = adminDao.queryAll(start,rows);
		return queryAll;
	}

	@Override
	public Admin queryById(String id) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.queryById(id);
		return admin;
	}

	@Override
	public void updateRole(String id, String role) {
		// TODO Auto-generated method stub
		adminDao.deleteFromURByUserId(id);
		adminDao.insertUserRole(id, role);
	}

	@Override
	public Integer queryTotal() {
		return adminDao.queryTotal();
	}

}
