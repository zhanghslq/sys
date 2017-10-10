package com.yb.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11 0011.
 */
public class Menu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String href;
    private String icon;
    private List<Menu> menus;

    public Menu(String id, String name, String href, String icon, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.href = href;
        this.icon = icon;
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", menus=" + menus +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    public Menu() {
        super();
    }
}
