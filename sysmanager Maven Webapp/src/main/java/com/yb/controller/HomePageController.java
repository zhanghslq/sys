package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.entity.HomePack;
import com.yb.entity.Price;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.HomePageService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/homePage")
public class HomePageController {
	@Resource
	private HomePageService homePageService;
	
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryMap")
	public Map<String, List> queryMap(Date start,Date end){
		List<HomePack> list = homePageService.queryList(start,end);
		List<String> days = new ArrayList<String>();
		List<Double> oil = new ArrayList<Double>();
		List<Double> notoil = new ArrayList<Double>();
		List<Double> avgWater = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (HomePack homePack : list) {
				days.add(homePack.getDate());
				oil.add(homePack.getOilMoney());
				notoil.add(homePack.getNotoilMoney());
				avgWater.add(homePack.getAvgWater());
			}
		}else {
			days.add("无数据 ");
			oil.add(0.0);
			notoil.add(0.0);
			avgWater.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days",days);
		map.put("oil",oil);
		map.put("notoil",notoil);
		map.put("avgWater",avgWater);
		return map;
		
	}
	@ResponseBody
	@RequestMapping("/exportOilShopAndWater")
	public void exportOilShopAndWater(Date start,Date end,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode("销售量和降水量情况.xls", "UTF-8");
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
        List<HomePack> list = homePageService.queryList(start,end);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("oilMoney", "油品交易额");
		titleMap.put("notoilMoney", "便利店交易额");
		titleMap.put("avgWater", "北京平均建水量");
		String sheetName = "销售量和降水量情况";
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
	@RequestMapping("/query")
	public Map<String,List> query(){
		List<DataPack> notOil = homePageService.queryNotOil();
		List<DataPack> oil = homePageService.queryOil();
		List<String> list = homePageService.queryAll();
		Map<String,List> map = new HashMap<String,List>();
		map.put("notOil",notOil );
		map.put("oil", oil);
		map.put("list",list);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryPriceBei")
	public Map<String, List> queryPriceBei(Date start,Date end){
		List<Price> list = homePageService.queryPriceBei(start,end);
		List<String> days = new ArrayList<String>();
		List<Double> oil89 = new ArrayList<Double>();
		List<Double> oil92 = new ArrayList<Double>();
		List<Double> oil95 = new ArrayList<Double>();
		List<Double> oil0 = new ArrayList<Double>();
		for (Price price : list) {
			oil89.add(price.getOil90());
			oil92.add(price.getOil93());
			oil95.add(price.getOil97());
			oil0.add(price.getOil0());
			days.add(simpleDateFormat.format(price.getTime()));
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("oil89",oil89 );
		map.put("oil92",oil92 );
		map.put("oil95",oil95 );
		map.put("oil0",oil0 );
		map.put("days",days);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportPriceBei")
	public void exportPriceBei(Date start,Date end,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode("北京第三方油价.xls", "UTF-8");
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
        List<Price> list = homePageService.queryPriceBei(start,end);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("time", "时间");
		titleMap.put("oil90", "89#油价");
		titleMap.put("oil93", "92#油价");
		titleMap.put("oil97", "95#油价");
		titleMap.put("oil0", "0#油价");
		String sheetName = "北京第三方 油价";
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
	@RequestMapping("/queryCheng")
	public Map<String, List> queryCheng(Date start,Date end){
		List<Price> list = homePageService.queryCheng(start,end);
		List<String> days = new ArrayList<String>();
		List<Double> oil90 = new ArrayList<Double>();
		List<Double> oil93 = new ArrayList<Double>();
		List<Double> oil97 = new ArrayList<Double>();
		List<Double> oil0 = new ArrayList<Double>();
		for (Price price : list) {
			oil90.add(price.getOil90());
			oil93.add(price.getOil93());
			oil97.add(price.getOil97());
			oil0.add(price.getOil0());
			days.add(simpleDateFormat.format(price.getTime()));
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("oil90",oil90 );
		map.put("oil93",oil93 );
		map.put("oil97",oil97 );
		map.put("oil0",oil0);
		map.put("days",days);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportCheng")
	public void exportCheng(Date start,Date end,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode("承德第三方油价.xls", "UTF-8");
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
        List<Price> list = homePageService.queryPriceBei(start,end);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("time", "时间");
		titleMap.put("oil90", "90#油价");
		titleMap.put("oil93", "93#油价");
		titleMap.put("oil97", "97#油价");
		titleMap.put("oil0", "0#油价");
		String sheetName = "承德第三方 油价";
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
