package com.yb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Heart;
import com.yb.service.HeartService;

@Controller
@Scope("prototype")
@RequestMapping("/heart")
public class HeartController {
	@Resource
	private HeartService heartService;

	@ResponseBody
	@RequestMapping("/queryAll")
	public List<Heart> queryAll(){
		List<Heart> list = heartService.queryAll();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryMessage")
	public String queryMessage(){
		String message="";
		List<Heart> list = heartService.queryByNumber(3, "server");
		if(list==null||list.size()==0){
			message="数据传输正常";
		}else {
			for (Heart heart : list) {
				message+=heart.getName()+" " +heart.getLastTime()+"<br/>";
			}
			message+="数据传输异常";
		}
		return message;
	}
	
}
