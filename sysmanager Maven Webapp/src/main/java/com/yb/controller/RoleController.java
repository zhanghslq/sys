package com.yb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Role;
import com.yb.entity.RolePack;
import com.yb.service.RoleService;

@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleController {
	@Resource
	private RoleService roleService;
	@RequestMapping("/checkName")
	@ResponseBody
	public Boolean checkName(String name){
		Role role = roleService.queryByName(name);
		if(role==null){
			return true;
		}else {
			return false;
		}
	}
	@ResponseBody
	@RequestMapping("/queryAllForEasy")
	public List<RolePack> queryAllForEasy(){
		List<Role> list = roleService.queryAll();
		List<RolePack> list2 = new ArrayList<RolePack>();
		/*Role queryById = roleService.queryByUserId(id);*/
		System.out.println("123");
		for (Role role : list) {
			/*if(role.getName().equals(queryById.getName())){
				list2.add(new RolePack(role.getId(), role.getName(), true));
			}else {*/
				list2.add(new RolePack(role.getId(), role.getName(), false));
			//}
		}
		return list2;
	}
	@ResponseBody
	@RequestMapping("/queryAll")
	public List<Role> queryAll(){
		List<Role> list = roleService.queryAll();
		
		return list;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id){
		roleService.delete(id);
	}
	@ResponseBody
	@RequestMapping("/insert")
	public void insert(Role role){
		roleService.insert(role);
	}
	@ResponseBody
	@RequestMapping("/queryById")
	public Role queryById(String id){
		Role role = roleService.queryById(id);
		return role;
	}
	@ResponseBody
	@RequestMapping("/update")
	public void update(Role role){
		roleService.update(role);
	}
	@ResponseBody
	@RequestMapping("/queryByUserId")
	public Role queryByUserId(String id){
		Role role = roleService.queryByUserId(id);
		return role;
	}
	@ResponseBody
	@RequestMapping("/grantPermission")
	public String grantPermission(String rid,String pid){
		try {
			if(pid!=null){
				String[] strs=pid.split(",");
				List<String> list=Arrays.asList(strs);
				roleService.updatePermission(rid, list);
			}
			return "修改成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "修改失败";
		}
	}
}
