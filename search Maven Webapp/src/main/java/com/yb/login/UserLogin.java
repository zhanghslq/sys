package com.yb.login;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baizhi.test.MD5Utils;
import com.yb.entity.Admin;
import com.yb.entity.Result;
import com.yb.service.ShiroService;
import com.yb.util.Sha1HmacUtil;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserLogin {
	@Resource
	private ShiroService shiroService;
	@RequestMapping(value="/login/{name}/{password}/{query}",method=RequestMethod.GET)
	@ResponseBody
	public Object login(@PathVariable("name")String name,
			@PathVariable("password")String password,
			@PathVariable("query")String query) throws Exception{
			 	try {
						Admin admin = shiroService.queryByName(name);
						if(admin==null){
							return new Result(1, "用户非法");
						}else if (MD5Utils.getDigest(admin.getSalt()+password).equals(admin.getPassword())) {
							//需要返回一個token
							//当前时间戳
							List<String> queryPermission = shiroService.queryPermission(name);
							if(query!=null&&queryPermission.contains(query)){
								long timeInMillis = Calendar.getInstance().getTimeInMillis();
								String original="m="+query+"&n="+name+"&p="+password+"&e=1800"+"&t="+timeInMillis;
								String hmacSHA1Encrypt = Sha1HmacUtil.HmacSHA1Encrypt(original, UUID.randomUUID().toString());
								String signature = Base64Utils.encodeToString((hmacSHA1Encrypt+original).getBytes());
								shiroService.insertToken(signature,query);
								return new Result(0, signature);
							}else {
								return new Result(1, "权限不足");
							}
						}else {
							return new Result(1, "用户账户密码不匹配");
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new Result(1, "获取signature出错");
				}
				
	        
	}
}


