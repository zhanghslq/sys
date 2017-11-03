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
	public String regist(Admin admin,HttpServletRequest request){
		try {
			adminService.regist(admin);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	@RequestMapping("/login")
	@ResponseBody
	public String login(String name,String password,String code,HttpServletRequest request){
		 try {
			 String attribute = (String)request.getSession().getAttribute("code");
			 	if(attribute.equalsIgnoreCase(code)){
			 		Subject subject = SecurityUtils.getSubject();
			 		subject.login(new UsernamePasswordToken(name,password));
			 		return "success";
			 	}else {
					return "code is wrong";
				}
	        } catch (AuthenticationException e) {
	            e.printStackTrace();
	            return "error";
	        }
	}
}
