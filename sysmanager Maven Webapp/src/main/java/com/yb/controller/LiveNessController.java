package com.yb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.InterPack;
import com.yb.entity.VipLiveness;
import com.yb.service.LiveNessService;

@Controller
@Scope("prototype")
@RequestMapping("/liveNess")
public class LiveNessController {
	@Resource
	private LiveNessService liveNessService;
	
	@ResponseBody
	@RequestMapping("/queryAllDate")
	public List<String> queryAllDate(){
		List<String> list = liveNessService.queryAllDate();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryData")
	public List<InterPack> queryData(String date){
		VipLiveness vipLiveness = liveNessService.queryData(date);
		List<InterPack> list = new ArrayList<InterPack>();
		list.add(new InterPack("未消费的", vipLiveness.getZero()));
		list.add(new InterPack("消费一次的",vipLiveness.getOne()));
		list.add(new InterPack("消费两次的", vipLiveness.getTwo()));
		list.add(new InterPack("消费三次的", vipLiveness.getThree()));
		list.add(new InterPack("三次以上的",vipLiveness.getOverThree()));
		return list;
	}
}
