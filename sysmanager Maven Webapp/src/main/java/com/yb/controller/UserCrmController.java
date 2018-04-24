package com.yb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Admin;
import com.yb.service.UserCrmService;

@Controller
@Scope("prototype")
@RequestMapping("/userCrm")
public class UserCrmController {
	@Resource
	private UserCrmService userCrmService;
	
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
	@RequestMapping("queryPermissionById")
	@ResponseBody
	public void queryPermissionById(String id){
		
	}
	@RequestMapping("/updatePermission")
	@ResponseBody
	public void updatePermission(String id,@RequestParam(required=false,value="permission[]")String[] permission){
		
	}
}
