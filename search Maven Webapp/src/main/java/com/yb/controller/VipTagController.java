package com.yb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yb.util.AESUtils;
import com.yb.util.RSAUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yb.entity.DataVip;
import com.yb.entity.QueryVipData;
import com.yb.entity.Result;
import com.yb.entity.VipTag;
import com.yb.service.TagActiveService;
import com.yb.service.VipTagService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/vipTag")
@Scope("prototype")
public class VipTagController {
	@Resource
	private VipTagService vipTagService;
	@Resource
	private TagActiveService tagActiveService;
	@RequestMapping(value="/queryTag/{signature}/{data}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryTag(@PathVariable("signature")String signature,
			@PathVariable("data")String data){

		DataVip parseObject = null;
		try {
			parseObject = JSON.parseObject(data, DataVip.class);
		} catch (Exception e) {
            System.out.println("未接收到参数");
		}
		try {
			if (parseObject != null) {//接受的参数不为空
				List<String> age = parseObject.getAge();
				String area = parseObject.getArea();
				List<String> coupon = parseObject.getCoupon();
				List<String> gender = parseObject.getGender();
				List<String> identity = parseObject.getIdentity();
				List<String> loyalty = parseObject.getLoyalty();
				List<String> manyStation = parseObject.getManyStation();
				List<String> mopType = parseObject.getMopType();
				List<String> oilName = parseObject.getOilName();
				Integer page = parseObject.getPage();
				List<String> recentNotOil = parseObject.getRecentNotOil();
				List<String> recentOil = parseObject.getRecentOil();
				Integer rows = parseObject.getRows();
				List<String> shopName = parseObject.getShopName();
				List<String> shortOil = parseObject.getShortOil();
				List<String> station = parseObject.getStation();
				List<String> tagActive = parseObject.getTagActive();
				List<String> type = parseObject.getType();
				if (area == null) {
					area = "BJSHELL";
				}
				if (page == null) {
					page = 1;
				}
				if (rows == null) {
					rows = 40;
				}
				if (rows > 100000) {
					rows = 100000;
				}
				List<Integer> integers = new ArrayList<Integer>();//id转换成Integer
				if (tagActive != null) {
					for (String string : tagActive) {
						integers.add(Integer.valueOf(string));
					}
				}
				List<String> queryAllVipTag = null;
				if (integers != null && integers.size() != 0) {
					queryAllVipTag = tagActiveService.queryAllVipTag(integers);//查出来的会员id
				}
				List<VipTag> list = vipTagService.query(loyalty, identity, gender,
						age, type, coupon, recentOil, recentNotOil,
						shortOil, station, oilName,
						shopName, mopType, page, rows, queryAllVipTag, manyStation, area);

                String s = JSON.toJSONString(list);
                byte[] bjshells = AESUtils.encrypt(s, "bjshell");
                String s1 = AESUtils.parseByte2HexStr(bjshells);
                return new Result(0, s1);
			}else{//接受的参数为空
					String area = "BJSHELL";
					Integer page = 1;
					Integer rows = 40;
				List<VipTag> list = vipTagService.query(null, null, null,
						null, null, null, null, null,
						null, null, null,
						null, null, page, rows, null, null, area);
                String s = JSON.toJSONString(list);
                byte[] bjshells = AESUtils.encrypt(s, "bjshell");
                String s1 = AESUtils.parseByte2HexStr(bjshells);
                return new Result(0, s1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(1, "请求出错");
		}
	}
	@ResponseBody
	@RequestMapping(value="/queryVip/{signature}/{data}",method=RequestMethod.GET)
	public Object queryVip(@PathVariable("signature")String signature,@PathVariable("data")String data){

		QueryVipData parseObject = null;
		try {
			parseObject = JSON.parseObject(data, QueryVipData.class);
		} catch (Exception e) {
            System.out.println("无参数");
		}
		try {
				if(parseObject!=null){//这个证明传递过来的有参数
					String date = parseObject.getDate();
					List<String> oilName = parseObject.getOilName();
					List<Integer> oilNumber = parseObject.getOilNumber();
					Integer page = parseObject.getPage();
					Integer rows = parseObject.getRows();
					List<String> shopName = parseObject.getShopName();
					List<String> station = parseObject.getStation();
					String area = parseObject.getArea();
					if(area==null){
						area="BJSHELL";
					}
					if(page==null){
						page=1;
					}
					if(rows==null){
						rows=40;
					}
					if(rows>100000){
						rows=100000;
					}
					if(date==null){
						date="null";
					}
					List<VipTag> list = vipTagService.queryVip(date, station, oilName, shopName, page, rows,area,oilNumber);
                    String s = JSON.toJSONString(list);
                    byte[] bjshells = AESUtils.encrypt(s, "bjshell");
                    String s1 = AESUtils.parseByte2HexStr(bjshells);
                    return new Result(0, s1);
				}else{//未接收到传递过来的参数
						String area="BJSHELL";
						Integer page=1;
						Integer rows=40;
						String date="null";
					List<VipTag> list = vipTagService.queryVip(date, null, null, null, page, rows,area,null);
                    String s = JSON.toJSONString(list);
                    byte[] bjshells = AESUtils.encrypt(s, "bjshell");
                    String s1 = AESUtils.parseByte2HexStr(bjshells);
                    return new Result(0, s1);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Result(1, "请求出错");
			}
	}
	@ResponseBody
	@RequestMapping(value="/test/{id}/{name}",method=RequestMethod.GET)
	public Object test(HttpServletRequest request,@PathVariable("id")String id,@PathVariable("name")String name){
		
		return id+"=============="+name;
	}
	
}
