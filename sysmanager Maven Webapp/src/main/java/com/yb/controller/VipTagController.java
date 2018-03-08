package com.yb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yb.entity.Description;
import com.yb.entity.TagGroup;
import com.yb.entity.VipTag;
import com.yb.service.TagActiveService;
import com.yb.service.TagGroupService;
import com.yb.service.VipTagService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/vipTag")
@Scope("prototype")
public class VipTagController {
	@Resource
	private VipTagService vipTagService;
	@Resource
	private TagGroupService tagGroupService;
	@Resource
	private TagActiveService tagActiveService;
	@SuppressWarnings("unused")
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(@RequestParam(required=false,value="loyalty[]")String[] loyalty,@RequestParam(required=false,value="identity[]")String[] identity,
			@RequestParam(required=false,value="gender[]")String[] gender,@RequestParam(required=false,value="age[]")String[] age,
			@RequestParam(required=false,value="type[]")String[] type,@RequestParam(required=false,value="coupon[]")String[] coupon,
			@RequestParam(required=false,value="recentOil[]")String[] recentOil,@RequestParam(required=false,value="recentNotOil[]")String[] recentNotOil,
			@RequestParam(required=false,value="shortOil[]")String[] shortOil,Integer page,Integer rows,
			@RequestParam(required=false,value="mopType[]")String[] mopType,@RequestParam(required=false,value="oilName[]")String[] oilName,
			@RequestParam(required=false,value="shopName[]")String[] shopName,@RequestParam(required=false,value="station[]")String[] station,
			@RequestParam(required=false,value="tagActive[]")String[] tagActive,@RequestParam(required=false,value="manyStation[]")String[] manyStation){
		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=40;
		}
		List<String> format = ArryToListUtil.format(tagActive);
		List<Integer> integers = new ArrayList<Integer>();//id转换成Integer
		if(format!=null){
			for (String string : format) {
				integers.add(Integer.valueOf(string));
			}
		}
		List<String> queryAllVipTag=null;
		if(integers!=null&&integers.size()!=0){
			queryAllVipTag= tagActiveService.queryAllVipTag(integers);//查出来的会员id
		}
		Integer start=(page - 1)*rows;
		Integer queryTotal = vipTagService.queryTotal(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil),ArryToListUtil.format(station),ArryToListUtil.format(oilName),
				ArryToListUtil.format(shopName),ArryToListUtil.format(mopType),queryAllVipTag,ArryToListUtil.format(manyStation));
		List<VipTag> list = vipTagService.query(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil),ArryToListUtil.format(station),ArryToListUtil.format(oilName),
				ArryToListUtil.format(shopName),ArryToListUtil.format(mopType),start, rows,queryAllVipTag,ArryToListUtil.format(manyStation));
		List<VipTag> listFormaTags=new ArrayList<VipTag>();
		for (VipTag vipTag : list) {
				vipTag.setMobilePhone(vipTag.getMobilePhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
				listFormaTags.add(vipTag);
				String tag = vipTag.getTag();
				String[] split = tag.split(" ", 5);
				List<String> format2 = ArryToListUtil.format(split);
				List<String> subList = format2.subList(0, format2.size()-1);
				StringBuilder builder = new StringBuilder();
				for (String string : subList) {
					builder.append(" "+string);
				}
				builder.append("...");
				vipTag.setTag(builder.toString());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(list!=null){
			map.put("rows", listFormaTags);
			map.put("total", queryTotal);
			return map;
		}
		return null;
	}
	@RequestMapping("/collect")
	@ResponseBody
	public String collect(@RequestParam(required=false,value="loyalty[]")String[] loyalty,@RequestParam(required=false,value="identity[]")String[] identity,
			@RequestParam(required=false,value="gender[]")String[] gender,@RequestParam(required=false,value="age[]")String[] age,
			@RequestParam(required=false,value="type[]")String[] type,@RequestParam(required=false,value="coupon[]")String[] coupon,
			@RequestParam(required=false,value="recentOil[]")String[] recentOil,@RequestParam(required=false,value="recentNotOil[]")String[] recentNotOil,
			@RequestParam(required=false,value="shortOil[]")String[] shortOil,String groupName,
			@RequestParam(required=false,value="mopType[]")String[] mopType,@RequestParam(required=false,value="oilName[]")String[] oilName,
			@RequestParam(required=false,value="shopName[]")String[] shopName,@RequestParam(required=false,value="station[]")String[] station,
			@RequestParam(required=false,value="tagActive[]")String[] tagActive,@RequestParam(required=false,value="manyStation[]")String[] manyStation){
		try {
			String loyaltys = JSONArray.toJSONString(ArryToListUtil.format(loyalty));
			String identitys = JSONArray.toJSONString(ArryToListUtil.format(identity));
			String genders = JSONArray.toJSONString(ArryToListUtil.format(gender));
			String ages = JSONArray.toJSONString(ArryToListUtil.format(age));
			String types = JSONArray.toJSONString(ArryToListUtil.format(type));
			String coupons = JSONArray.toJSONString(ArryToListUtil.format(coupon));
			String recentOils = JSONArray.toJSONString(ArryToListUtil.format(recentOil));
			String recentNotOils = JSONArray.toJSONString(ArryToListUtil.format(recentNotOil));
			String shortOils = JSONArray.toJSONString(ArryToListUtil.format(shortOil));
			String stations = JSONArray.toJSONString(ArryToListUtil.format(station));
			String oilNames = JSONArray.toJSONString(ArryToListUtil.format(oilName));
			String shopNames = JSONArray.toJSONString(ArryToListUtil.format(shopName));
			String mopTypes = JSONArray.toJSONString(ArryToListUtil.format(mopType));
			String tagActives = JSONArray.toJSONString(ArryToListUtil.format(tagActive));
			String manyStations = JSONArray.toJSONString(ArryToListUtil.format(manyStation));
			TagGroup tagGroup = new TagGroup(null, loyaltys, identitys, genders, ages, types, coupons, recentOils, recentNotOils, shortOils, mopTypes,
					oilNames, shopNames, stations, groupName,tagActives,manyStations);
			tagGroupService.insert(tagGroup);
			return "收藏成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "收藏出错";
		}
	}
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
	@ResponseBody
	@RequestMapping("/queryAllDescription")
	public List<Description> queryAllDescription(){
		List<Description> list = vipTagService.queryAllDescriptions();
		return list;
	}
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping("/queryVip")
	public Map<String, Object> queryVip(String date,@RequestParam(required=false,value="station[]")String[] station,
			@RequestParam(value="oilName[]",required=false)String[] oils,
			@RequestParam(value="shopName[]",required=false)String[] shops,Integer page,Integer rows){
			if(page==null){
				page=1;
			}
			if(rows==null){
				rows=40;
			}
			if(date==null){
				date="null";
			}
			Integer start=(page - 1)*rows;
			Integer queryVipTototal = vipTagService.queryVipTototal(date, ArryToListUtil.format(station), ArryToListUtil.format(oils), ArryToListUtil.format(shops));
			List<VipTag> list = vipTagService.queryVip(date, ArryToListUtil.format(station), ArryToListUtil.format(oils), ArryToListUtil.format(shops), start, rows);
			List<VipTag> listFormaTags=new ArrayList<VipTag>();
			for (VipTag vipTag : list) {
					vipTag.setMobilePhone(vipTag.getMobilePhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
					listFormaTags.add(vipTag);
					String tag = vipTag.getTag();
					String[] split = tag.split(" ", 5);
					List<String> format2 = ArryToListUtil.format(split);
					List<String> subList = format2.subList(0, format2.size()-1);
					StringBuilder builder = new StringBuilder();
					for (String string : subList) {
						builder.append(" "+string);
					}
					builder.append("...");
					vipTag.setTag(builder.toString());
			}
			Map<String,Object> map = new HashMap<String,Object>();
			if(list!=null){
				map.put("rows", listFormaTags);
				map.put("total", queryVipTototal);
				return map;
			}
			return map;
	}
}
