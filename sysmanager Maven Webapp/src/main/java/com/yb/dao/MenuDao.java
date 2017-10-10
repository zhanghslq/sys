package com.yb.dao;

import java.util.List;

import com.yb.entity.Menu;

/**
 * Created by Administrator on 2017/6/11 0011.
 */
public interface MenuDao {
    public List<Menu> queryAll();
    public Menu queryByParent(String pid);
}
