package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.yb.entity.InterPack;
import com.yb.entity.VipLiveness;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.LiveNessService;

@Controller
@Scope("prototype")
@RequestMapping("/liveNess")
public class LiveNessController {
	@Resource
	private LiveNessService liveNessService;
	
	@ResponseBody
	@RequestMapping("/queryAllDate")
	public List<String> queryAllDate(String area){
		List<String> list = liveNessService.queryAllDate(area);
		return list;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryData")
	public Map<String, List> queryData(String area){
		List<VipLiveness> list = liveNessService.queryData(area);
		List<String> date = new ArrayList<String>();
		List<Integer> zero = new ArrayList<Integer>();
		List<Integer> one = new ArrayList<Integer>();
		List<Integer> two = new ArrayList<Integer>();
		List<Integer> three = new ArrayList<Integer>();
		List<Integer> four = new ArrayList<Integer>();
		List<Integer> five= new ArrayList<Integer>();
		List<Integer> overfive= new ArrayList<Integer>();
		if(list!=null&&list.size()!=0){
			for (VipLiveness vipLiveness : list) {
				date.add(vipLiveness.getMonth());
				zero.add(vipLiveness.getZero());
				one.add(vipLiveness.getOne());
				two.add(vipLiveness.getTwo());
				three.add(vipLiveness.getThree());
				four.add(vipLiveness.getFour());
				five.add(vipLiveness.getFive());
				overfive.add(vipLiveness.getOverfive());
			}
		}else {
			date.add("无数据");
			zero.add(0);
			one.add(0);
			two.add(0);
			three.add(0);
			four.add(0);
			five.add(0);
			overfive.add(0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("date",date);
		map.put("zero",zero);
		map.put("one",one);
		map.put("two",two);
		map.put("three",three);
		map.put("four",four);
		map.put("five",five);
		map.put("overfive",overfive);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportData")
	public void exportOils(String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(abc+"消费频次.xls", "UTF-8");
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
        List<VipLiveness> list = liveNessService.queryData(area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("month", "时间");
		titleMap.put("zero", "未消费的");
		titleMap.put("one", "消费一次的");
		titleMap.put("two", "消费两次的");
		titleMap.put("three", "消费三次的");
		titleMap.put("four", "消费四次的");
		titleMap.put("five", "消费五次的");
		titleMap.put("overfive", "五次以上的");
		String sheetName = "会员消费频次";
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
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryDataByStation")
	public Map<String, List> queryDataByStation(String station){
		List<VipLiveness> list = liveNessService.queryLivessByStation(station);
		List<String> date = new ArrayList<String>();
		List<Integer> zero = new ArrayList<Integer>();
		List<Integer> one = new ArrayList<Integer>();
		List<Integer> two = new ArrayList<Integer>();
		List<Integer> three = new ArrayList<Integer>();
		List<Integer> four = new ArrayList<Integer>();
		List<Integer> five= new ArrayList<Integer>();
		List<Integer> overfive= new ArrayList<Integer>();
		if(list!=null&&list.size()!=0){
			for (VipLiveness vipLiveness : list) {
				date.add(vipLiveness.getMonth());
				zero.add(vipLiveness.getZero());
				one.add(vipLiveness.getOne());
				two.add(vipLiveness.getTwo());
				three.add(vipLiveness.getThree());
				four.add(vipLiveness.getFour());
				five.add(vipLiveness.getFive());
				overfive.add(vipLiveness.getOverfive());
			}
		}else {
			date.add("无数据");
			zero.add(0);
			one.add(0);
			two.add(0);
			three.add(0);
			four.add(0);
			five.add(0);
			overfive.add(0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("date",date);
		map.put("zero",zero);
		map.put("one",one);
		map.put("two",two);
		map.put("three",three);
		map.put("four",four);
		map.put("five",five);
		map.put("overfive",overfive);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportLiveNessByStation")
	public void exportLiveNessByStation(String station,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(station+"消费频次.xls", "UTF-8");
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
        List<VipLiveness> list = liveNessService.queryLivessByStation(station);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("month", "时间");
		titleMap.put("one", "消费一次的");
		titleMap.put("two", "消费两次的");
		titleMap.put("three", "消费三次的");
		titleMap.put("four", "消费四次的");
		titleMap.put("five", "消费五次的");
		titleMap.put("overfive", "五次以上的");
		String sheetName = "油站消费频次";
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
	@ResponseBody
	@RequestMapping("/queryDataByDate")
	public List<InterPack> queryDataByDate(String month,String area){
		VipLiveness liveness = liveNessService.queryDataByDate(month,area);
		List<InterPack> list = new ArrayList<InterPack>();
		if(liveness!=null){
			list.add(new InterPack("未消费的", liveness.getZero()));
			list.add(new InterPack("消费一次的", liveness.getOne()));
			list.add(new InterPack("消费两次的", liveness.getTwo()));
			list.add(new InterPack("消费三次的", liveness.getThree()));
			list.add(new InterPack("消费四次的", liveness.getFour()));
			list.add(new InterPack("消费五次的", liveness.getFive()));
			list.add(new InterPack("五次以上的", liveness.getOverfive()));
		}else {
			list.add(new InterPack("无数据", 0));
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryAllYearDate")
	public List<String> queryAllYearDate(String area){
		List<String> list = liveNessService.queryAllYearDate(area);
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryLiveNessByYear")
	public List<InterPack> queryLiveNessByYear(String year,String area){
		VipLiveness liveness = liveNessService.queryLiveNessByYear(area, year);
		List<InterPack> list = new ArrayList<InterPack>();
		if(liveness!=null){
			list.add(new InterPack("未消费的",liveness.getZero()));
			list.add(new InterPack("一到五次的", liveness.getOne()));
			list.add(new InterPack("六到十次的", liveness.getTwo()));
			list.add(new InterPack("十一到十五次的", liveness.getThree()));
			list.add(new InterPack("十六到二十次的", liveness.getFour()));
			list.add(new InterPack("二十一到二十五", liveness.getFive()));
			list.add(new InterPack("二十六次及以上", liveness.getOverfive()));
		}else {
			list.add(new InterPack("未消费的",0));
			list.add(new InterPack("一到五次的", 0));
			list.add(new InterPack("六到十次的", 0));
			list.add(new InterPack("十一到十五次的", 0));
			list.add(new InterPack("十六到二十次的", 0));
			list.add(new InterPack("二十一到二十五", 0));
			list.add(new InterPack("二十六次及以上", 0));
			
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/exportLiveNessByYear")
	public void exportLiveNessByYear(String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(abc+"年消费频次.xls", "UTF-8");
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
        List<VipLiveness> list = liveNessService.queryLiveNessYear(area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("month", "时间");
		titleMap.put("zero", "未消费的");
		titleMap.put("one", "消费一到五次的");
		titleMap.put("two", "消费六到十次的");
		titleMap.put("three", "消费十一到十五次的");
		titleMap.put("four", "消费十六到二十次的");
		titleMap.put("five", "消费二十一到二十五次的");
		titleMap.put("overfive", "二十六次及以上的");
		String sheetName = "会员年消费频次";
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
