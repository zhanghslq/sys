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

import com.yb.entity.Coupon;
import com.yb.entity.DataPack;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.CouponService;

@Controller
@RequestMapping("/coupon")
@Scope("prototype")
public class CounponController {
	@Resource
	private CouponService couponService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(Date start,Date end,String date){
		List<Coupon> list = couponService.query(start, end,date);
		List<String> days = new ArrayList<String>();
		List<Double> all = new ArrayList<Double>();
		List<Double> used = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Coupon coupon: list) {
				days.add(coupon.getDays());
				all.add(coupon.getAllMoney());
				used.add(coupon.getUsedMoney());
			}
		}else {
			days.add("无数据");
			all.add(0.0);
			used.add(0.0);
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("all", all);
		map.put("used", used);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportCoupon")
	public void exportOils(HttpServletResponse response,Date start,Date end,String query,String area,String date){
		String encode="";
		try {
			encode = URLEncoder.encode("优惠券使用情况.xls", "UTF-8");
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
        List<Coupon> list = couponService.query(start, end,date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("allMoney", "发放金额");
		titleMap.put("usedMoney", "核销金额");
		String sheetName = "优惠券使用情况";
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
	@RequestMapping("/queryZhanbi")
	@ResponseBody
	public Map<String,List> queryZhanbi(){
		List<DataPack> list = couponService.queryZhanbi();
		List<String> names = new ArrayList<String>();
		for (DataPack dataPack : list) {
			names.add(dataPack.getName());
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("names", names);
		map.put("data", list);
		return map;
	}
}
