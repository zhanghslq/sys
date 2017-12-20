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

import com.yb.entity.DataPack;
import com.yb.entity.Station;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.ProductService;
import com.yb.service.StationService;

@Controller
@RequestMapping("/product")
@Scope("prototype")
public class ProductController {

	@Resource
	private ProductService productService;
	@Resource
	private StationService stationService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryProduct")
	@ResponseBody
	public Map<String, List> queryProduct(Date start,Date end,String station){
		List<DataPack> list = productService.queryProduct(start, end, station);
		List<String> dates = new ArrayList<String>();
		List<Double> data = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack : list) {
				dates.add(dataPack.getName());
				data.add(dataPack.getValue());
			}
		}else {
			dates.add("无数据");
			data.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("data", data);
		return map;
	}
	@RequestMapping("/exportProduct")
	@ResponseBody
	public void exportProduct(HttpServletResponse response,Date start,Date end,String station){
		String encode="";
		Station queryById = stationService.queryById(station);
		String staString=null;
		if(queryById!=null){
			staString=queryById.getName();
		}
		try {
			encode = URLEncoder.encode(staString+"劳动生产率.xlsx", "UTF-8");
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
		
		List<DataPack> list = productService.queryProduct(start, end, station);
		if(list==null||list.size()==0){
			list.add(new DataPack("无数据",0.0));
		}
		//
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "时间");
		titleMap.put("value", "劳动生产率");
		String sheetName = staString+"劳动生产率";
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
