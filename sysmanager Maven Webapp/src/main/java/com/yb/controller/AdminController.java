package com.yb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baizhi.test.MD5Utils;
import com.yb.entity.Admin;
import com.yb.service.AdminService;

@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminService adminService;
	//检查用户名,是否已存在
	@RequestMapping("/checkName")
	@ResponseBody
	public Boolean checkName(String name){
		Admin admin = adminService.queryByName(name);
		if(admin==null){
			return true;
		}else {
			System.out.println("false");
			return false;
		}
	}
	@ResponseBody
	@RequestMapping("/regist")
	public String regist(Admin admin,String code,HttpServletRequest request){
		try {
			String attribute = (String)request.getSession().getAttribute("code");
		 	if(attribute.equalsIgnoreCase(code)){
		 		adminService.regist(admin);
		 		return "success";
		 	}else {
				return "code";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "注册失败，请重试";
		}
	}
	@RequestMapping("/login")
	@ResponseBody
	public String login(String name,String password,String code,HttpServletRequest request){
			 	try {
					String attribute = (String)request.getSession().getAttribute("code");
					if(attribute.equalsIgnoreCase(code)){
						Admin admin = adminService.queryByName(name);
						if(admin==null){
							return "name";
						}
						Subject subject = SecurityUtils.getSubject();
						subject.login(new UsernamePasswordToken(name,password));
						return "success";
					}else {
						return "code";
					}
				} catch (AuthenticationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "password";
				}
	        
	}
	@ResponseBody
	@RequestMapping("/update")
	public String update(String name,String oldpassword,String newpassword){
		try {
			Admin admin = adminService.queryByName(name);
			if(admin.getPassword().equals(MD5Utils.getDigest(admin.getSalt()+oldpassword))){
				String digest = MD5Utils.getDigest(admin.getSalt()+newpassword);
				adminService.update(name, digest);
				return "success";
			}
			return "error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
}
