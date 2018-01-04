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
}
