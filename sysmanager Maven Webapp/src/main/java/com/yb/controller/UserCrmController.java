package com.yb.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Admin;
import com.yb.entity.PermissionPack;
import com.yb.service.UserCrmService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/userCrm")
public class UserCrmController {
	@Resource
	private UserCrmService userCrmService;
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Admin> queryAll(){
		List<Admin> list = userCrmService.queryAll();
		return list;
	}
	
	@RequestMapping("/checkName")
	@ResponseBody
	public Boolean checkName(String name){
		Admin queryByName = userCrmService.queryByName(name);
		if(queryByName==null){
			return true;
		}else{
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/regist")
	public void regist(Admin admin){
		 try {
			 userCrmService.insert(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id){
		try {
			userCrmService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/update")
	public void update(String name,String password){
		userCrmService.update(name, password);
	}
	@RequestMapping("/queryPermissionByName")
	@ResponseBody
	public List<PermissionPack> queryPermissionById(String name){
		List<PermissionPack> list = userCrmService.queryPermissionByUser(name);
		return list;
	}
	@RequestMapping("/updatePermission")
	@ResponseBody
	public String updatePermission(String uname,String permission){
		try {
			if(permission!=null){
				String[] strs=permission.split(",");
				List<String> list=Arrays.asList(strs);
				userCrmService.updatePermission(uname, list);
			}
			return "授权成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "授权失败";
		}
	}
	@RequestMapping("/queryByName")
	@ResponseBody
	public Admin queryByName(String name){
		Admin admin = userCrmService.queryByName(name);
		return admin;
	}
}
