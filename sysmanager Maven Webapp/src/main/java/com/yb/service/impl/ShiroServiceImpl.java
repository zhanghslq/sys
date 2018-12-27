package com.yb.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.ShiroDao;
import com.yb.entity.Permission;
import com.yb.entity.Role;
import com.yb.service.ShiroService;

/**
 *
 * @author Administrator
 * @date 2017/6/20 0020
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private ShiroDao shiroDao;
    @Override
    public List<Permission> queryPermission(String name) {
        List<Permission> permissions = shiroDao.queryPermission(name);
        return permissions;
    }
    @Override
    public List<Role> queryRole(String name) {
        List<Role> roles = shiroDao.queryRole(name);
        return roles;
    }
}
