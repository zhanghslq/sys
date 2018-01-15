package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.AddVip;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.AddVipService;
import com.yb.util.DateFormatUtils;

@Controller
@RequestMapping("/addVip")
@Scope("prototype")
public class AddVipController {
	//全网新增会员
	@Resource
	private AddVipService addVipService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(String date,Date start,Date end,String area){
		List<AddVip> list = addVipService.query(date, start, end,area);
		List<String> dates = new ArrayList<String>();
		List<Integer> addNumbers = new ArrayList<Integer>();
		List<Integer> totalPeoples = new ArrayList<Integer>();
		for (AddVip addVip : list) {
			dates.add(addVip.getDate());
			addNumbers.add(addVip.getNumber());
			totalPeoples.add(addVip.getTotalPeople());
		}
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("addNumbers", addNumbers);
		map.put("totalPeoples", totalPeoples);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportAddVip")
	public void exportOils(String date,Date start,Date end,String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(abc+"会员招募.xls", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		OutputStream os=null;
        try {
			os= new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //获取需要导出的集合信息
        List<AddVip> list = addVipService.query(date, start, end,area);
		
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("number", "招募人数");
		titleMap.put("totalPeople", "会员总数");
		String sheetName = "会员招募信息";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName);
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			os.flush();
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
	//dashboard会员情况
	//现有会员，当日新增，本月累计活跃会员and占比,本月累计新增会员
	//会员近七天油品销量，近七天便利店销售额
	//会员当日油品交易额占比，便利店交易额占比
	//本月累计油品交易额占比，便利店交易额占比
	//本月累计油品优惠券核销率，便利店优惠券核销率，（暂无）
	//当日充值，本月充值，今年累计充值
	//评分系统，当日评分，当月评分，评价条数
	public void queryDashBoard(){
		Date date = new Date();
		Integer vipnow=null;//现有会员人数
		Integer addDay=null;//当日新增
		Integer addMonth=null;//本月新增
		Integer activeInteger=null;//活跃会员人数
		String activity=null;//活跃人数占比
		
		
		List<AddVip> dayBj = addVipService.query("day", date, date, "BJSHELL");
		if(dayBj!=null&&dayBj.size()!=0){
			for (AddVip addVip : dayBj) {
				addDay=addVip.getNumber();//新招募会员
				vipnow=addVip.getTotalPeople();//总会员人数
			}
		}
		List<AddVip> monthBj = addVipService.query("month", DateFormatUtils.getMonthStart(), date, "BJSHELL");
		if(monthBj!=null&&monthBj.size()!=0){
			for (AddVip addVip : monthBj) {
				Integer number = addVip.getNumber();
				
			}
		}
		List<AddVip> monthCD = addVipService.query("month", DateFormatUtils.getMonthStart(), date, "CDSHELL");
		List<AddVip> dayCD = addVipService.query("day", date, date, "CDSHELL");
		Map<String,Object> map = new HashMap<String,Object>();
		
		
	}
	
}
