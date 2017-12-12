package com.yb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.VipTag;
import com.yb.service.VipTagService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/vipTag")
@Scope("prototype")
public class VipTagController {
	@Resource
	private VipTagService vipTagService;
	/*"loyalty":jqchk("loyalty"),"identity":jqchk("identity"),"gender":jqchk("gender"),
	"age":jqchk("age"),"type":jqchk("type"),"coupon":jqchk("coupon"),
	"recentOil":jqchk("recentOil"),"recentNotOil":jqchk("recentNotOil"),"shortOil":jqchk("shortOil"),*/
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(@RequestParam(required=false,value="loyalty[]")String[] loyalty,@RequestParam(required=false,value="identity[]")String[] identity,
			@RequestParam(required=false,value="gender[]")String[] gender,@RequestParam(required=false,value="age[]")String[] age,
			@RequestParam(required=false,value="type[]")String[] type,@RequestParam(required=false,value="coupon[]")String[] coupon,
			@RequestParam(required=false,value="recentOil[]")String[] recentOil,@RequestParam(required=false,value="recentNotOil[]")String[] recentNotOil,
			@RequestParam(required=false,value="shortOil[]")String[] shortOil,Integer index,Integer number){
		if(index==null){
			index=0;
		}
		if(number==null){
			number=20;
		}
		Integer queryTotal = vipTagService.queryTotal(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil));
		List<VipTag> list = vipTagService.query(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil), index, number);
		Map<String,Object> map = new HashMap<String,Object>();
		if(list!=null){
			map.put("rows", list);
			map.put("total", queryTotal);
			return map;
		}
		return null;
	}
	@ResponseBody
	@RequestMapping("/queryTotal")
	public Integer queryTotal(@RequestParam(required=false,value="loyalty[]")String[] loyalty,@RequestParam(required=false,value="identity[]")String[] identity,
			@RequestParam(required=false,value="gender[]")String[] gender,@RequestParam(required=false,value="age[]")String[] age,
			@RequestParam(required=false,value="type[]")String[] type,@RequestParam(required=false,value="coupon[]")String[] coupon,
			@RequestParam(required=false,value="recentOil[]")String[] recentOil,@RequestParam(required=false,value="recentNotOil[]")String[] recentNotOil,
			@RequestParam(required=false,value="shortOil[]")String[] shortOil){
		Integer queryTotal = vipTagService.queryTotal(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil));
		return queryTotal;
	}
}
