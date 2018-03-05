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

import com.yb.entity.Recharge;
import com.yb.entity.Rechargeb;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.RechargeService;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/recharge")
@Scope("prototype")
public class RechargeController {
	@Resource
	private RechargeService rechargeService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(String date,Date start,Date end,String area){
		List<Recharge> list = rechargeService.query(date, start, end,area);
		List<String> dates = new ArrayList<String>();
		List<Double> tradeAmounts = new ArrayList<Double>();
		List<Double> avgAmounts = new ArrayList<Double>();
		List<Long> tradeNumber = new ArrayList<Long>();
		Map<String,List> map= new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Recharge recharge : list) {
				dates.add(recharge.getDate());
				tradeAmounts.add(recharge.getTotalAmount());
				avgAmounts.add(DoubleFormatUtil.format(recharge.getAvgAmount()));
				tradeNumber.add(recharge.getTradeNumber());
			}
		}else {
			dates.add("无数据");
			tradeAmounts.add((double) 0);
			avgAmounts.add((double) 0);
			tradeNumber.add(Long.valueOf(0));
		}
		map.put("dates", dates);
		map.put("tradeAmounts", tradeAmounts);
		map.put("avgAmounts", avgAmounts);
		map.put("tradeNumber", tradeNumber);
		return map;
	}
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/queryByType")
	public Map<String, List> queryByType(String date,Date start,Date end,String area){
		List<Rechargeb> list = rechargeService.queryByType(date, start, end, area);
		List<String> dates = new ArrayList<String>();
		List<Double> jdAmount = new ArrayList<Double>();
		List<Double> wxAmount= new ArrayList<Double>();
		List<Integer> jdNumber = new ArrayList<Integer>();
		List<Integer> wxNumber= new ArrayList<Integer>();
		Map<String,List> map= new HashMap<String,List>();
		if(list!=null&&list.size()!=0){
			for (Rechargeb rechargeb : list) {
				dates.add(rechargeb.getDate());
				jdAmount.add(rechargeb.getJdtotalAmount());
				jdNumber.add(rechargeb.getJdtradeNumber());
				wxAmount.add(rechargeb.getWxtotalAmount());
				wxNumber.add(rechargeb.getWxtradeNumber());
			}
		}else {
			dates.add("无数据");
			jdAmount.add(0.0);
			jdNumber.add(0);
			wxAmount.add(0.0);
			wxNumber.add(0);
		}
		map.put("dates", dates);
		map.put("jdAmount",jdAmount);
		map.put("jdNumber",jdNumber);
		map.put("wxAmount",wxAmount);
		map.put("wxNumber",wxNumber);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportRechargeByType")
	public void exportRechargeByType(HttpServletResponse response,String date,Date start,Date end,String area){
		String encode="";
		String abcString="";
		try {
			if("BJSHELL".equals(area)){
				abcString="北京";
			}
			if("CDSHELL".equals(area)){
				abcString="承德";
			}
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+abcString+"会员充值分类.xls", "UTF-8");
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
        List<Rechargeb> list = rechargeService.queryByType(date, start, end, area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("jdtradeNumber", "京东充值笔数");
		titleMap.put("jdtotalAmount", "京东充值金额");
		titleMap.put("wxtradeNumber", "微信充值笔数");
		titleMap.put("wxtotalAmount", "微信充值金额");
		String sheetName = "会员充值情况";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
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
	@RequestMapping("/exportRecharge")
	public void exportOils(HttpServletResponse response,String date,Date start,Date end,String area){
		String encode="";
		String abcString="";
		try {
			if("BJSHELL".equals(area)){
				abcString="北京";
			}
			if("CDSHELL".equals(area)){
				abcString="承德";
			}
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+abcString+"会员充值情况.xls", "UTF-8");
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
        List<Recharge> list = rechargeService.query(date, start, end,area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("totalAmount", "充值总额");
		titleMap.put("avgAmount", "单笔充值金额");
		titleMap.put("tradeNumber", "充值笔数");
		String sheetName = "会员充值情况";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start,end);
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
