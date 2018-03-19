package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yb.entity.Group;
import com.yb.entity.TagGroup;
import com.yb.entity.VipTag;
import com.yb.excel.test.one.ExportExcelUtils;
import com.yb.service.TagActiveService;
import com.yb.service.TagGroupService;
import com.yb.service.VipTagService;

@Controller
@Scope("prototype")
@RequestMapping("/tagGroup")
public class TagGroupController {
	@Resource
	private TagGroupService tagGroupService;
	@Resource
	private VipTagService vipTagService;
	@Resource
	private TagActiveService tagActiveService;
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Group> queryAll(){
		List<TagGroup> queryAll = tagGroupService.queryAll();
		List<Group> list = new LinkedList<Group>();
		for (TagGroup tagGroup : queryAll) {
			List<String> age = JSONArray.parseArray(tagGroup.getAge(), String.class);
			List<String> coupon = JSONArray.parseArray(tagGroup.getCoupon(), String.class);
			List<String> gender = JSONArray.parseArray(tagGroup.getGender(), String.class);
			String groupName=tagGroup.getGroupName();
			List<String> identity = JSONArray.parseArray(tagGroup.getIdentity(), String.class);
			List<String> loyalty = JSONArray.parseArray(tagGroup.getLoyalty(), String.class);
			List<String> mopType = JSONArray.parseArray(tagGroup.getMopType(), String.class);
			List<String> oilName = JSONArray.parseArray(tagGroup.getOilName(), String.class);
			List<String> recentOil = JSONArray.parseArray(tagGroup.getRecentOil(), String.class);
			List<String> recentNotOil = JSONArray.parseArray(tagGroup.getRecentNotOil(), String.class);
			List<String> shopName = JSONArray.parseArray(tagGroup.getShopName(), String.class);
			List<String> shortOil = JSONArray.parseArray(tagGroup.getShortOil(), String.class);
			List<String> station = JSONArray.parseArray(tagGroup.getStation(), String.class);
			List<String> type = JSONArray.parseArray(tagGroup.getType(), String.class);
			List<Integer> parseArray = JSONArray.parseArray(tagGroup.getActive(), Integer.class);
			List<String> manyStation = JSONArray.parseArray(tagGroup.getManyStation(), String.class);
			
			List<String> list3=null;
			if(parseArray!=null&&parseArray.size()!=0){
				list3= tagActiveService.queryAllVipTag(parseArray);
				if(list3.size()==0){
					list3=null;
				}
			}
			Integer id = tagGroup.getId();
			Integer total = vipTagService.queryTotal(loyalty, identity, gender, age, type, coupon, recentOil, recentNotOil, shortOil, station, oilName, shopName, mopType,list3,manyStation,tagGroup.getArea());
			list.add(new Group(id, groupName, total));
		}
		return list;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Integer id){
		try {
			tagGroupService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/exportById")
	@ResponseBody
	public void exportById(Integer id,HttpServletResponse response){
		String encode = null;
		try {
			encode = URLEncoder.encode("会员信息导出.xlsx", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        OutputStream os = null;
		try {
			os = new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		TagGroup tagGroup = tagGroupService.queryById(id);
		if(tagGroup!=null){
			List<String> age = JSONArray.parseArray(tagGroup.getAge(), String.class);
			List<String> coupon = JSONArray.parseArray(tagGroup.getCoupon(), String.class);
			List<String> gender = JSONArray.parseArray(tagGroup.getGender(), String.class);
			List<String> identity = JSONArray.parseArray(tagGroup.getIdentity(), String.class);
			List<String> loyalty = JSONArray.parseArray(tagGroup.getLoyalty(), String.class);
			List<String> mopType = JSONArray.parseArray(tagGroup.getMopType(), String.class);
			List<String> oilName = JSONArray.parseArray(tagGroup.getOilName(), String.class);
			List<String> recentOil = JSONArray.parseArray(tagGroup.getRecentOil(), String.class);
			List<String> recentNotOil = JSONArray.parseArray(tagGroup.getRecentNotOil(), String.class);
			List<String> shopName = JSONArray.parseArray(tagGroup.getShopName(), String.class);
			List<String> shortOil = JSONArray.parseArray(tagGroup.getShortOil(), String.class);
			List<String> station = JSONArray.parseArray(tagGroup.getStation(), String.class);
			List<String> type = JSONArray.parseArray(tagGroup.getType(), String.class);
			List<String> active = JSONArray.parseArray(tagGroup.getActive(), String.class);
			List<String> manyStation = JSONArray.parseArray(tagGroup.getManyStation(), String.class);
			List<Integer> list2 = new ArrayList<Integer>();
			if(active!=null&&active.size()!=0){
				for (String string : active) {
					list2.add(Integer.valueOf(string));
				}
			}
			List<String> list3=null;
			if(list2!=null&&list2.size()!=0){
				list3= tagActiveService.queryAllVipTag(list2);
			}
			List<List<String>> data = new LinkedList<List<String>>();//存放数据的
	        List<VipTag> list=null;
	        String[] headers = { "编号", "用户名","手机号","标签"};  
	        ExportExcelUtils eeu = new ExportExcelUtils();  
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        int start=0;
	        int num=0;
	        //原理就是将所有的数据一起写入，然后再关闭输入流。  
	        while (true) {//死循环
	           num++;
	           list = vipTagService.query(loyalty, identity, gender, age, type, coupon, recentOil, recentNotOil, shortOil, station, oilName, shopName, mopType,start, 60000,list3,manyStation,tagGroup.getArea());
	    	   start+=60000;//让开始位置的加60000
	    	   if(list==null||list.size()==0){
	    		   break;//跳出while循环
	    	   }
		    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
		    		   for (VipTag vipTag : list) {
		        		   List<String>rowData = new LinkedList<String>();
		        		   rowData.add(vipTag.getCarduser_id());  
		        		   rowData.add(vipTag.getName());
		        		   rowData.add(vipTag.getMobilePhone());
		        		   rowData.add(tagGroup.getGroupName());
		        		   data.add(rowData);
		        	   }
		        	   try {
		    			eeu.exportExcel(workbook, num-1, "会员信息"+num, headers, data);
		    			data.clear();//把数据写入之后清除，等待下次的数据
		    			} catch (Exception e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}  
				}
	        }//while结束
	        try {
				workbook.write(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
	}
}
