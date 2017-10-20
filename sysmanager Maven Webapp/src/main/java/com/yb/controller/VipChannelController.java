package com.yb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.Channel;
import com.yb.entity.InterPack;
import com.yb.service.VipChannelService;

@Controller
@RequestMapping("/vipChannel")
@Scope("prototype")
public class VipChannelController {
	@Resource
	private VipChannelService vipChannelService;
	
	@RequestMapping("/queryChannel")
	@ResponseBody
	public List<InterPack> queryChannel(Date start,Date end){
		Channel channel = vipChannelService.queryChannel(start, end);
		ArrayList<InterPack> list = new ArrayList<InterPack>();
		if(channel!=null){
			list.add(new InterPack("未知",channel.getUnknown())) ;
			list.add(new InterPack("支付宝",channel.getAplipay()));
			list.add(new InterPack("手机",channel.getMobile()));
			list.add(new InterPack("PC",channel.getPc()));
			list.add(new InterPack("微信",channel.getWechat()));
		}else {
			list.add(new InterPack("未知",0)) ;
			list.add(new InterPack("支付宝",0));
			list.add(new InterPack("手机",0));
			list.add(new InterPack("PC",0));
			list.add(new InterPack("微信",0));
		}
		return list;
	}
}
