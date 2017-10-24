package com.yb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.InterPack;
import com.yb.entity.VipFunnel;
import com.yb.service.VipFunnelService;

@Controller
@RequestMapping("/vipFunnel")
@Scope("prototype")
public class VipFunnelController {
	
	@Resource
	private VipFunnelService vipFunnelService;
	
	@RequestMapping("/queryAllMonth")
	@ResponseBody
	public List<String> queryAllMonth(){
		List<String> list = vipFunnelService.queryAllMonth();
		return list;
	}
	@RequestMapping("/queryVipFunnel")
	@ResponseBody
	public List<InterPack> queryVipFunnel(String month){
		VipFunnel funnel = vipFunnelService.queryVipFunnel(month);
		List<InterPack> list = new ArrayList<InterPack>();
		if(funnel!=null){
			list.add(new InterPack("至少消费一次的", funnel.getAtLeastOne()));
			list.add(new InterPack("活跃会员",funnel.getLiveness()));
			list.add(new InterPack("从未消费的", funnel.getNoDeal()));
			list.add(new InterPack("会员总数",funnel.getSum()));
		}else {
			list.add(new InterPack("至少消费一次的", 0));
			list.add(new InterPack("活跃会员",0));
			list.add(new InterPack("从未消费的", 0));
			list.add(new InterPack("会员总数",0));
		}
		return list;
	}
}
