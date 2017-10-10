package com.yb.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Menu;
import com.yb.service.MenuService;

/**
 * Created by Administrator on 2017/6/11 0011.
 */
@Controller
@Scope("prototype")
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;
    @ResponseBody
    @RequestMapping("/queryAll")
    public List<Menu> queryAll(HttpServletResponse response) throws IOException {
        List<Menu> menus = menuService.queryAll();
        return menus;
    }
}
