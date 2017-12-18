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
			@RequestParam(required=false,value="shortOil[]")String[] shortOil,Integer page,Integer rows,
			@RequestParam(required=false,value="mopType[]")String[] mopType,@RequestParam(required=false,value="oilName[]")String[] oilName,
			@RequestParam(required=false,value="shopName[]")String[] shopName,@RequestParam(required=false,value="station[]")String[] station){
		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=20;
		}
		Integer start=(page - 1)*rows;
		Integer queryTotal = vipTagService.queryTotal(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil),ArryToListUtil.format(station),ArryToListUtil.format(oilName),
				ArryToListUtil.format(shopName),ArryToListUtil.format(mopType));
		List<VipTag> list = vipTagService.query(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil),ArryToListUtil.format(station),ArryToListUtil.format(oilName),
				ArryToListUtil.format(shopName),ArryToListUtil.format(mopType),start, rows);
		Map<String,Object> map = new HashMap<String,Object>();
		if(list!=null){
			map.put("rows", list);
			map.put("total", queryTotal);
			return map;
		}
		return null;
	}
	/*@ResponseBody
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
	}*/
	@ResponseBody
	@RequestMapping("/queryAllMop")
	public List<String> queryAllMop(){
		List<String> list = vipTagService.queryAllMop();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryAllOil")
	public List<String> queryAllOil(){
		List<String> list = vipTagService.queryAllOil();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryAllShop")
	public List<String> queryAllShop(){
		List<String> list = vipTagService.queryAllShop();
		return list;
	}
	
	
}
