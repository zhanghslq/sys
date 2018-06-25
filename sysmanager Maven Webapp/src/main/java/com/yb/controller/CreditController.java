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

import com.yb.entity.Credit;
import com.yb.entity.DataPack;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.CreditService;

@Controller
@RequestMapping("/credit")
@Scope("prototype")
public class CreditController {

	@Resource
	private CreditService creditService;
	@RequestMapping("/queryCredits")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Map<String, List> queryCredits(String date,Date start,Date end,String area){
		List<Credit> list = creditService.queryCredit(date, start, end,area);
		List<String> days = new ArrayList<String>();
		List<Double> get = new ArrayList<Double>();
		List<Double> used = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Credit credit : list) {
				days.add(credit.getDays());
				get.add(credit.getGetCredits());
				used.add(credit.getUsedCredits());
			}
		}else {
			days.add("无数据");
			get.add(0.0);
			used.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("get", get);
		map.put("used", used);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportCredit")
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
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+abcString+"会员积分发放与使用情况.xls", "UTF-8");
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
        List<Credit> list = creditService.queryCredit(date, start, end,area);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("getCredits", "发放积分");
		titleMap.put("usedCredits", "使用积分");
		String sheetName = "会员积分发放与使用情况";
		//应该是要返回一个hsswork然后os响应出来
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
	@RequestMapping("/queryZhanbi")
	@ResponseBody
	public List<DataPack> queryZhanbi(String area){
		Credit credit = creditService.queryZhanbi(area);
		List<DataPack> list = new ArrayList<DataPack>();
		list.add(new DataPack("已使用", credit.getUsedCredits()));
		list.add(new DataPack("未使用", credit.getGetCredits()));
		return list;
	}
	@ResponseBody
	@RequestMapping("/exportZhanbi")
	public void exportZhanbi(HttpServletResponse response,String area){
		String encode="";
		String abcString="";
		try {
			if("BJSHELL".equals(area)){
				abcString="北京";
			}
			if("CDSHELL".equals(area)){
				abcString="承德";
			}
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+abcString+"会员积分余量占比.xls", "UTF-8");
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
        Credit credit = creditService.queryZhanbi(area);
        List<Credit> list = new ArrayList<Credit>();
        list.add(credit);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("getCredits", "未使用");
		titleMap.put("usedCredits", "已使用");
		String sheetName = "会员积分余量占比";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,new Date(),new Date());
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
