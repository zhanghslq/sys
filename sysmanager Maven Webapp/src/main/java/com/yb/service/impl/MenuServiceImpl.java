package com.yb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yb.dao.MenuDao;
import com.yb.entity.Menu;
import com.yb.service.MenuService;

/**
 * Created by Administrator on 2017/6/11 0011.
 */
@Service("menuService")
public class MenuServiceImpl  implements MenuService {
    @Autowired
    private MenuDao menuDao;

    public List<Menu> queryAll() {
        List<Menu> menus = menuDao.queryAll();
        return menus;
    }
}
