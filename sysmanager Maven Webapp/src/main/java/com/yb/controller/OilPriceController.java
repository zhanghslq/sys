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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.DataPack;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.OilPriceService;

@Controller
@RequestMapping("/oilPrice")
@Scope("prototype")
public class OilPriceController {

	@Resource
	private OilPriceService oilPriceService;
	
	@ResponseBody
	@RequestMapping("/queryAllName")
	public List<String> queryAllName(){
		List<String> list = oilPriceService.queryAllName();
		return list;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryPrice")
	@ResponseBody
	public Map<String, List> queryPrice(Date start,Date end,String station,String oilName){
		if(oilName==null){
			oilName="92#";
		}
		List<DataPack> list = oilPriceService.queryPrice(start, end, station, oilName);
		List<String> dates = new ArrayList<String>();
		List<Double> prices = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				dates.add(dataPack.getName());
				prices.add(dataPack.getValue());
			}
		}else {
			dates.add("无数据");
			prices.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("prices", prices);
		return map;
	}
	/**
	 * 油价调整情况的导出    等待使用
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param station  油站id
	 * @param oilName 油品名字
	 * 
	 */
	@ResponseBody
	@RequestMapping("/exportPrice")
	public void exportPrice(Date start,Date end,String station,String oilName,HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+station+oilName+"油价调整.xls", "UTF-8");
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
        /**
         * 获取需要导出的集合信息
         */
        List<DataPack> list = oilPriceService.queryPrice(start, end, station, oilName);
        
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "时间");
		titleMap.put("value", "油价");
		String sheetName = "油价调整情况";
		/**
		 * 应该是要返回一个hsswork然后os响应出来
		 */
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
		try {
			excelExport.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
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

}
