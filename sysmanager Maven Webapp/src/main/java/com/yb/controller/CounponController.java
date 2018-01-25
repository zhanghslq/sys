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
import com.yb.entity.Couponb;
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
		List<Couponb> list = couponService.queryByType(start, end,date);
		List<String> days = new ArrayList<String>();
		List<Double> oilScore = new ArrayList<Double>();
		List<Double> oilreissued = new ArrayList<Double>();
		List<Double> oilorder = new ArrayList<Double>();
		List<Double> oilorderNum = new ArrayList<Double>();
		List<Double> oilhfive = new ArrayList<Double>();
		List<Double> oilscoreused = new ArrayList<Double>();
		List<Double> oilreissuedused = new ArrayList<Double>();
		List<Double> oilorderused = new ArrayList<Double>();
		List<Double> oilordernumused = new ArrayList<Double>();
		List<Double> oilhfiveused = new ArrayList<Double>();
		List<Double> shopScore = new ArrayList<Double>();
		List<Double> shopReissued = new ArrayList<Double>();
		List<Double> shopOrder = new ArrayList<Double>();
		List<Double> shophfive = new ArrayList<Double>();
		List<Double> shopScoreUsed = new ArrayList<Double>();
		List<Double> shopReissuedUsed = new ArrayList<Double>();
		List<Double> shopOrderUsed = new ArrayList<Double>();
		List<Double> shophfiveUsed = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (Couponb couponb: list) {
				days.add(couponb.getDays());
				oilScore.add(couponb.getOil_score_allmoney());
				oilreissued.add(couponb.getOil_reissued_allmoney());
				oilorder.add(couponb.getOil_order_allmoney());
				oilorderNum.add(couponb.getOil_order_allnum());
				oilhfive.add(couponb.getOil_hfive_allmoney());
				oilscoreused.add(couponb.getOil_score_allmoney());
				oilreissuedused.add(couponb.getOil_reissued_usedmoney());
				oilorderused.add(couponb.getOil_order_usedmoney());
				oilordernumused.add(couponb.getOil_order_usednum());
				oilhfiveused.add(couponb.getOil_hfive_usedmoney());
				shopScore.add(couponb.getNotoil_score_allmoney());
				shopReissued.add(couponb.getNotoil_reissued_allmoney());
				shopOrder.add(couponb.getNotoil_order_allmoney());
				shophfive.add(couponb.getNotoil_hfive_allmoney());
				shopScoreUsed.add(couponb.getNotoil_score_usedmoney());
				shopReissuedUsed.add(couponb.getNotoil_reissued_allmoney());
				shopOrderUsed.add(couponb.getNotoil_order_usedmoney());
				shophfiveUsed.add(couponb.getNotoil_hfive_usedmoney());
			}
		}else {
			days.add("无数据");
		}
		Map<String,List> map = new HashMap<String,List>();
		map.put("days", days);
		map.put("oilScore", oilScore);//燃油积分兑换
		map.put("oilreissued",oilreissued);//燃油人工赠送
		map.put("oilorder",oilorder);//燃油会员活动
		map.put("oilorderNum", oilorderNum);//会员活动折扣
		map.put("oilhfive",oilhfive );//H5活动发放
		map.put("oilscoreused",oilscoreused );//燃油积分兑换核销
		map.put("oilreissuedused",oilreissuedused );//燃油人工赠送核销
		map.put("oilorderused",oilorderused );//燃油会员活动核销
		map.put("oilordernumused",oilordernumused );//会员活动折扣核销
		map.put("oilhfiveused",oilhfiveused );//H5活动赠送核销
		map.put("shopScore",shopScore );//非油积分兑换
		map.put("shopReissued",shopReissued );//非油人工赠送
		map.put("shopOrder",shopOrder);//非油会员活动
		map.put("shophfive",shophfive );//非油H5活动
		map.put("shopScoreUsed",shopScoreUsed );//非油积分兑换核销
		map.put("shopReissuedUsed",shopReissuedUsed );//非油人工赠送核销
		map.put("shopOrderUsed",shopOrderUsed);//非油会员活动核销
		map.put("shophfiveUsed",shophfiveUsed);//非油H5活动核销
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
