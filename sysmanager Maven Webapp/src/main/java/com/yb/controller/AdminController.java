package com.yb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baizhi.test.MD5Utils;
import com.yb.entity.Admin;
import com.yb.service.AdminService;


/**
 * 用户
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
    private final Logger log=LoggerFactory.getLogger(AdminController.class);

	@Resource
	private AdminService adminService;
	/**
	 * 检查用户名,是否已存在
	 */
	@RequestMapping("/checkName")
	@ResponseBody
	public Boolean checkName(String name){
		Admin admin = adminService.queryByName(name);
		if(admin==null){
			return true;
		}else {
			return false;
		}
	}
	@ResponseBody
	@RequestMapping("/regist")
	public void regist(Admin admin,String code,HttpServletRequest request){
		 try {
			adminService.regist(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id){
		try {
			adminService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			System.out.println("name="+name+"oldpassword="+oldpassword+"newpassword="+newpassword);
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
	@ResponseBody
	@RequestMapping("/updateRole")
	public void updateRole(String id,String role){
		try {
			adminService.updateRole(id, role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/queryAll")
	public Map<String, Object> queryAll(Integer page,Integer rows){

		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=20;
		}
		Integer start=(page - 1)*rows;
		List<Admin> list = adminService.queryAll(start,rows);

		Integer integer = adminService.queryTotal();

		Map<String, Object> map = new HashMap<>(4);


        if(list!=null&&list.size()!=0){
            map.put("total",integer);
            map.put("rows",list);
            return map;
        }
        return null;


	}
	@ResponseBody
	@RequestMapping("/queryById")
	public Admin queryById(String id){
		 Admin admin = adminService.queryById(id);
		return admin;
	}
}
