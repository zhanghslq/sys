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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.CouponAll;
import com.yb.entity.CouponByType;
import com.yb.entity.CouponData;
import com.yb.entity.CouponNature;
import com.yb.entity.CouponOil;
import com.yb.entity.CouponSource;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;
import com.yb.entity.Station;
import com.yb.entity.Tactics;
import com.yb.excel.test.one.ExportExcelUtils;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.CouponService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;

@Controller
@RequestMapping("/coupon")
@Scope("prototype")
public class CounponController {
	@Resource
	private CouponService couponService;
	@Resource
	private StationService stationService;
	/**
	 * 根据优惠券的类型查询的来源
	 * @param start
	 * @param end
	 * @param date
	 * @param oil
	 * @param type
	 * @param city
	 * @return
	 */
	@RequestMapping("/querySource")
	@ResponseBody
	public Map<String,Object> querySource(Date start,Date end,String date,String oil,String type,Integer city){
		if(city==null){
			//为城市赋值
			city=6;
		}
		if("null".equals(oil)||"".equals(oil)){
			oil=null;
		}
		if("null".equals(type)||"".equals(type)){
			type=null;
		}
		List<Integer> list = new ArrayList<Integer>();//放置优惠券种类
		if("oil".equals(oil)&&"discount".equals(type)){
			list.add(2);//油品折扣1
		}else if ("oil".equals(oil)&&"del".equals(type)) {
			list.add(1);//油品满减
		}else if ("shop".equals(oil)&&"del".equals(type)) {
			list.add(3);//便利店满减
		}else if("shop".equals(oil)&&"discount".equals(type)){
			list.add(4);//便利店折扣
		}else if (oil==null&&"discount".equals(type)) {
			//不限制的折扣
			list.add(2);
			list.add(4);
		}else if (oil==null&&"del".equals(type)) {
			//不限制的满减
			list.add(1);
			list.add(3);
		}else if ("shop".equals(oil)&&type==null) {
			//不限制种类的便利店
			list.add(4);
			list.add(3);
		}else if ("oil".equals(oil)&&type==null) {
			//不限制种类的油品
			list.add(1);
			list.add(2);
		}else {
			list=null;
		}
		List<String> days=new ArrayList<String>();
		List<Integer> scoreAll=new ArrayList<Integer>();
		List<Integer> scoreUsed=new ArrayList<Integer>();
		List<Integer> orderAll=new ArrayList<Integer>();
		List<Integer> orderUsed=new ArrayList<Integer>();
		List<Integer> reissuedAll=new ArrayList<Integer>();
		List<Integer> reissuedUsed=new ArrayList<Integer>();
		List<Integer> otherAll=new ArrayList<Integer>();
		List<Integer> otherUsed=new ArrayList<Integer>();
		List<CouponSource> querySource = couponService.querySource(city, list, start, end, date);
		for (CouponSource couponSource : querySource) {
			days.add(couponSource.getDays());
			scoreAll.add(couponSource.getScore_allmoney());
			scoreUsed.add(couponSource.getScore_usedmoney());
			orderAll.add(couponSource.getOrder_allmoney());
			orderUsed.add(couponSource.getOrder_usedmoney());
			reissuedAll.add(couponSource.getReissued_allmoney());
			reissuedUsed.add(couponSource.getReissued_usedmoney());
			otherAll.add(couponSource.getOther_allmoney());
			otherUsed.add(couponSource.getOther_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("scoreAll",scoreAll );
		map.put("scoreUsed",scoreUsed );
		map.put("orderAll", orderAll);
		map.put("orderUsed",orderUsed );
		map.put("reissuedAll", reissuedAll);
		map.put("reissuedUsed",reissuedUsed );
		map.put("otherAll", otherAll);
		map.put("otherUsed",otherUsed );
		return map;
	}
	@ResponseBody
	@RequestMapping("/queryOil")
	public Map<String,Object> queryOil(Date start,Date end,String date,Integer city,String type,String coupon){
		if("null".equals(coupon)||"".equals(coupon)){
			coupon=null;
		}
		if("null".equals(type)||"".equals(type)){
			type=null;
		}
		
		List<String> days = new ArrayList<String>();
		List<Integer> oilAll=new ArrayList<Integer>();
		List<Integer> oilUsed=new ArrayList<Integer>();
		List<Integer> shopAll=new ArrayList<Integer>();
		List<Integer> shopUsed=new ArrayList<Integer>();
		List<CouponOil> queryOil = couponService.queryOil(city, type, coupon, start, end, date);
		for (CouponOil couponOil : queryOil) {
			days.add(couponOil.getDays());
			oilAll.add(couponOil.getOil_allmoney());
			oilUsed.add(couponOil.getOil_usedmoney());
			shopAll.add(couponOil.getNotoil_allmoney());
			shopUsed.add(couponOil.getNotoil_usedmoney());
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("oilAll",oilAll );
		map.put("oilUsed",oilUsed );
		map.put("shopAll", shopAll);
		map.put("shopUsed",shopUsed );
		return map;
	}
	@ResponseBody
	@RequestMapping("/queryNature")
	public Map<String, Object> queryNature(Date start,Date end,String date,Integer city,String type,String coupon){
		if("null".equals(type)||"".equals(type)){
			type=null;
		}
		if("null".equals(coupon)||"".equals(coupon)){
			coupon=null;
		}
		List<String> days = new ArrayList<String>();
		List<Integer> delAll = new ArrayList<Integer>();
		List<Integer> delUsed= new ArrayList<Integer>();
		List<Integer> disAll = new ArrayList<Integer>();
		List<Integer> disUsed = new ArrayList<Integer>();
		List<CouponNature> queryNature = couponService.queryNature(city, type, coupon, start, end, date);
		for (CouponNature couponNature : queryNature) {
			days.add(couponNature.getDays());
			delAll.add(couponNature.getOff_allmoney());
			delUsed.add(couponNature.getOff_usedmoney());
			disAll.add(couponNature.getDiscount_allmoney());
			disUsed.add(couponNature.getDiscount_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("delAll",delAll );
		map.put("delUsed", delUsed);
		map.put("disAll", disAll);
		map.put("disUsed", disUsed);
		return map;
	}
	@ResponseBody
	@RequestMapping("queryDiscSource")
	public Map<String, Object> queryDiscSource(Date start,Date end,String date,Integer city){
		List<String> days=new ArrayList<String>();
		List<Integer> scoreAll=new ArrayList<Integer>();
		List<Integer> scoreUsed=new ArrayList<Integer>();
		List<Integer> orderAll=new ArrayList<Integer>();
		List<Integer> orderUsed=new ArrayList<Integer>();
		List<Integer> reissuedAll=new ArrayList<Integer>();
		List<Integer> reissuedUsed=new ArrayList<Integer>();
		List<Integer> otherAll=new ArrayList<Integer>();
		List<Integer> otherUsed=new ArrayList<Integer>();
		List<CouponSource> queryDisSource = couponService.queryDisSource(city, start, end, date);
		for (CouponSource couponSource : queryDisSource) {
			days.add(couponSource.getDays());
			scoreAll.add(couponSource.getScore_allmoney());
			scoreUsed.add(couponSource.getScore_usedmoney());
			orderAll.add(couponSource.getOrder_allmoney());
			orderUsed.add(couponSource.getOrder_usedmoney());
			reissuedAll.add(couponSource.getReissued_allmoney());
			reissuedUsed.add(couponSource.getReissued_usedmoney());
			otherAll.add(couponSource.getOther_allmoney());
			otherUsed.add(couponSource.getOther_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("scoreAll",scoreAll );
		map.put("scoreUsed",scoreUsed );
		map.put("orderAll", orderAll);
		map.put("orderUsed",orderUsed );
		map.put("reissuedAll", reissuedAll);
		map.put("reissuedUsed",reissuedUsed );
		map.put("otherAll", otherAll);
		map.put("otherUsed",otherUsed );
		return map;
	}
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
	@RequestMapping("/queryScore")
	public Map<String, Object> queryScore(Date start,Date end,String date){
		List<CouponAll> list = couponService.queryScore(start, end, date);
		List<String> days = new ArrayList<String>();
		List<Integer> oilGive = new ArrayList<Integer>();
		List<Integer> oilUsed= new ArrayList<Integer>();
		List<Integer> shopGive = new ArrayList<Integer>();
		List<Integer> shopUsed = new ArrayList<Integer>();
		for (CouponAll couponAll : list) {
			days.add(couponAll.getDays());
			oilGive.add(couponAll.getOil_allmoney());
			oilUsed.add(couponAll.getOil_usedmoney());
			shopGive.add(couponAll.getNotoil_allmoney());
			shopUsed.add(couponAll.getNotoil_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("oilGive", oilGive);
		map.put("oilUsed", oilUsed);
		map.put("shopGive", shopGive);
		map.put("shopUsed", shopUsed);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/queryTactics")
	public Map<String, Object> queryTactics(Date start,Date end){
		List<Tactics> list = couponService.queryTactics(start, end);
		List<String> names = new ArrayList<String>();
		List<Integer> allNumber= new ArrayList<Integer>();
		List<Integer> usedNumber= new ArrayList<Integer>();
		for (Tactics tactics : list) {
			names.add(tactics.getTacticsTitle());
			allNumber.add(tactics.getAllNumber());
			usedNumber.add(tactics.getUsedNumber());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("names", names);
		map.put("allNumber", allNumber);
		map.put("usedNumber", usedNumber);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportTactics")
	public void exportTactics(HttpServletResponse response,Date start,Date end){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠券活动.xls", "UTF-8");
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
		List<Tactics> list = couponService.queryTactics(start, end);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("month", "时间");
		titleMap.put("send_tactics_id", "活动ID");
		titleMap.put("tacticsTitle", "活动名称");
		titleMap.put("allNumber", "发放数量");
		titleMap.put("usedNumber","核销数量" );
		String sheetName = "优惠券活动";
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
	@ResponseBody
	@RequestMapping("/exportSource")
	public void exportSource(HttpServletResponse response,Date start,Date end,String type,String oil,Integer city,String date){
		if("null".equals(oil)||"".equals(oil)){
			oil=null;
		}
		if("null".equals(type)||"".equals(type)){
			type=null;
		}
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠发放与核销（来源）.xls", "UTF-8");
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
        if(city==null){
			//为城市赋值
			city=6;
		}
		List<Integer> list = new ArrayList<Integer>();//放置优惠券种类
		if("oil".equals(oil)&&"discount".equals(type)){
			list.add(2);//油品折扣1
		}else if ("oil".equals(oil)&&"del".equals(type)) {
			list.add(1);//油品满减
		}else if ("shop".equals(oil)&&"del".equals(type)) {
			list.add(3);//便利店满减
		}else if("shop".equals(oil)&&"discount".equals(type)){
			list.add(4);//便利店折扣
		}else if (oil==null&&"discount".equals(type)) {
			//不限制的折扣
			list.add(2);
			list.add(4);
		}else if (oil==null&&"del".equals(type)) {
			//不限制的满减
			list.add(1);
			list.add(3);
		}else if ("shop".equals(oil)&&type==null) {
			//不限制种类的便利店
			list.add(4);
			list.add(3);
		}else if ("oil".equals(oil)&&type==null) {
			//不限制种类的油品
			list.add(1);
			list.add(2);
		}else {
			list=null;
		}
		List<CouponSource> querySource = couponService.querySource(city, list, start, end, date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		
		titleMap.put("score_allmoney", "积分发放");
		titleMap.put("order_allmoney","活动发放" );
		titleMap.put("reissued_allmoney", "人工赠送");
		titleMap.put("other_allmoney", "其他活动发放");
		titleMap.put("score_usedmoney","积分兑换核销" );
		titleMap.put("order_usedmoney","活动发放核销" );
		titleMap.put("reissued_usedmoney","人工赠送核销" );
		titleMap.put("other_usedmoney","其他活动核销" );
		String sheetName = "优惠券发放与核销（来源）";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(querySource, titleMap, sheetName,start,end);
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
	/**
	 * 积分兑换数据导出
	 * @param response
	 * @param start
	 * @param end
	 * @param query
	 * @param area
	 * @param date
	 */
	
	@ResponseBody
	@RequestMapping("/exportOilNotOil")
	public void exportScore(HttpServletResponse response,Date start,Date end,String date,Integer city,String type,String coupon){
		if("null".equals(type)||"".equals(type)){
			type=null;
		}
		if("null".equals(coupon)||"".equals(coupon)){
			coupon=null;
		}
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠发放与核销（商品）.xls", "UTF-8");
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
		List<CouponOil> queryOil = couponService.queryOil(city, type, coupon, start, end, date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("oil_allmoney", "燃油发放");
		titleMap.put("notoil_allmoney", "非油发放");
		titleMap.put("oil_usedmoney", "燃油核销");
		titleMap.put("notoil_usedmoney","非油核销" );
		String sheetName = "优惠券发放与核销（商品）";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(queryOil, titleMap, sheetName,start,end);
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
	@ResponseBody
	@RequestMapping("/exportCouponData")
	public void exportCouponData(HttpServletResponse response,Date start,Date end,String date,String oil,String type){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠发放与核销（来源）源数据.xls", "UTF-8");
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
		List<String> test = new ArrayList<String>();//放置优惠券种类
		if("oil".equals(oil)&&"discount".equals(type)){
			test.add("");//油品折扣1
		}else if ("oil".equals(oil)&&"del".equals(type)) {
			test.add("燃油满减券");//油品满减
		}else if ("shop".equals(oil)&&"del".equals(type)) {
			test.add("非油满减券");//便利店满减
		}else if("shop".equals(oil)&&"discount".equals(type)){
			test.add("非油折扣券");//便利店折扣
		}else if (oil==null&&"discount".equals(type)) {
			//不限制的折扣
			test.add("燃油折扣券");
			test.add("非油折扣券");
		}else if (oil==null&&"del".equals(type)) {
			//不限制的满减
			test.add("燃油满减券");
			test.add("非油满减券");
		}else if ("shop".equals(oil)&&type==null) {
			//不限制种类的便利店
			test.add("非油折扣券");
			test.add("非油满减券");
		}else if ("oil".equals(oil)&&type==null) {
			//不限制种类的油品
			test.add("燃油满减券");
			test.add("燃油折扣券");
		}else {
			test=null;
		}
		List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
        List<CouponData> list=null;
        String[] headers = { "优惠券编号", "优惠券名字","发放时间","核销时间","优惠券来源","优惠券类型","优惠券金额","核销油站编号","优惠券状态"};  
        ExportExcelUtils eeu = new ExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int st=0;
        int count=60000;
        int num=0;
        
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
           list =  couponService.exportData2(start, end, null, null, test, st, count);
    	   st+=60000;//让开始位置的加60000
    	   if(list==null||list.size()==0){
    		   break;//跳出while循环
    	   }
	    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
	    		   for (CouponData couponData : list){
	        		   List<Object>rowData = new LinkedList<Object>();
	        		   rowData.add(couponData.getID());  
	        		   rowData.add(couponData.getCoupon_title());
	        		   rowData.add(couponData.getSend_time());
	        		   rowData.add(couponData.getUsed_time());
	        		   rowData.add(couponData.getTactics_type());
	        		   rowData.add(couponData.getCoupon_type());
	        		   rowData.add(couponData.getDiscount_amount());
	        		   rowData.add(couponData.getStation_id());
	        		   rowData.add(couponData.getCoupon_status());
	        		   data.add(rowData);
	        	   }
	        	   try {
	    			eeu.exportExcel(workbook, num-1, "优惠券源数据"+num, headers, data);
	    			data.clear();//把数据写入之后清除，等待下次的数据
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
			}
        }//while结束
        try {
			workbook.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
			//获取需要导出的集合信息
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
	/**
	 * 积分发放源数据
	 * @param response
	 * @param start
	 * @param end
	 * @param query
	 * @param area
	 * @param date
	 */
	@ResponseBody
	@RequestMapping("/exportCouponScoreData")
	public void exportCouponScoreData(HttpServletResponse response,Date start,Date end,String date,Integer city,String type,String coupon){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠发放与核销（商品）源数据.xls", "UTF-8");
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
		List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
        List<CouponData> list=null;
        String[] headers = { "优惠券编号", "优惠券名字","发放时间","核销时间","优惠券来源","优惠券类型","优惠券金额","核销油站编号","优惠券状态"};  
        ExportExcelUtils eeu = new ExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int st=0;
        int count=60000;
        int num=0;
		if (type==null) {
			
		}
        else if("score".equals(type)){
        	type="积分兑换";
        }else if("order".equals(type)){
        	type="活动赠送";
        }else if ("other".equals(type)) {
			type="人工赠送";
		}
		List<String> arrayList = new ArrayList<String>();
		if("del".equals(coupon)){
			arrayList.add("燃油满减券");
			arrayList.add("非油满减券");
		}else if ("discount".equals(coupon)) {
			arrayList.add("燃油折扣券");
			arrayList.add("非油折扣券");
		}else {
			arrayList=null;
		}
		if("null".equals(type)||"".equals(type)){
			type=null;
		}
		if("null".equals(coupon)||"".equals(coupon)){
			coupon=null;
		}
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
           list = couponService.exportData2(start, end, null, type, arrayList, st, count);
    	   st+=60000;//让开始位置的加60000
    	   if(list==null||list.size()==0){
    		   break;//跳出while循环
    	   }
	    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
	    		   for (CouponData couponData : list){
	        		   List<Object>rowData = new LinkedList<Object>();
	        		   rowData.add(couponData.getID());  
	        		   rowData.add(couponData.getCoupon_title());
	        		   rowData.add(couponData.getSend_time());
	        		   rowData.add(couponData.getUsed_time());
	        		   rowData.add(couponData.getTactics_type());
	        		   rowData.add(couponData.getCoupon_type());
	        		   rowData.add(couponData.getDiscount_amount());
	        		   rowData.add(couponData.getStation_id());
	        		   rowData.add(couponData.getCoupon_status());
	        		   data.add(rowData);
	        	   }
	        	   try {
	    			eeu.exportExcel(workbook, num-1, "优惠券源数据"+num, headers, data);
	    			data.clear();//把数据写入之后清除，等待下次的数据
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
			}
        }//while结束
		try {
			workbook.write(os);
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
	@ResponseBody
	@RequestMapping("/ExportDisSource")
	public void exportDisSource(HttpServletResponse response,Date start,Date end,String type,String oil,Integer city,String date){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"折扣优惠发放与核销（来源）.xls", "UTF-8");
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
        if(city==null){
			//为城市赋值
			city=6;
		}
		List<Integer> list = new ArrayList<Integer>();//放置优惠券种类
		if("oil".equals(oil)&&"discount".equals(type)){
			list.add(2);//油品折扣1
		}else if ("oil".equals(oil)&&"del".equals(type)) {
			list.add(1);//油品满减
		}else if ("shop".equals(oil)&&"del".equals(type)) {
			list.add(3);//便利店满减
		}else if("shop".equals(oil)&&"discount".equals(type)){
			list.add(4);//便利店折扣
		}else if (oil==null&&"discount".equals(type)) {
			//不限制的折扣
			list.add(2);
			list.add(4);
		}else if (oil==null&&"del".equals(type)) {
			//不限制的满减
			list.add(1);
			list.add(3);
		}else if ("shop".equals(oil)&&type==null) {
			//不限制种类的便利店
			list.add(4);
			list.add(3);
		}else if ("oil".equals(oil)&&type==null) {
			//不限制种类的油品
			list.add(1);
			list.add(2);
		}else {
			list=null;
		}
		List<CouponSource> querySource = couponService.querySource(city, list, start, end, date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("score_allmoney", "积分发放");
		titleMap.put("order_allmoney","活动发放" );
		titleMap.put("reissued_allmoney", "人工赠送");
		titleMap.put("other_allmoney", "其他活动发放");
		titleMap.put("score_usedmoney","积分兑换核销" );
		titleMap.put("order_usedmoney","活动发放核销" );
		titleMap.put("reissued_usedmoney","人工赠送核销" );
		titleMap.put("other_usedmoney","其他活动核销" );
		String sheetName = "折扣优惠券发放与核销（来源）";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(querySource, titleMap, sheetName,start,end);
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
	@ResponseBody
	@RequestMapping("/exportCouponDataOil")
	public void exportCouponDataOil(HttpServletResponse response,Date start,Date end,String query,String area){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"折扣优惠券发放与核销（来源）源数据.xls", "UTF-8");
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
		//获取需要导出的集合信息List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
		List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
        List<CouponData> list=null;
        String[] headers = { "优惠券编号", "优惠券名字","发放时间","核销时间","优惠券来源","优惠券类型","优惠券金额","核销油站编号","优惠券状态"};  
        ExportExcelUtils eeu = new ExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int st=0;
        int count=60000;
        int num=0;
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("燃油折扣券");
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
           list = couponService.exportData2(start, end, null, null, arrayList, st, count);
    	   st+=60000;//让开始位置的加60000
    	   if(list==null||list.size()==0){
    		   break;//跳出while循环
    	   }
	    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
	    		   for (CouponData couponData : list){
	        		   List<Object>rowData = new LinkedList<Object>();
	        		   rowData.add(couponData.getID());  
	        		   rowData.add(couponData.getCoupon_title());
	        		   rowData.add(couponData.getSend_time());
	        		   rowData.add(couponData.getUsed_time());
	        		   rowData.add(couponData.getTactics_type());
	        		   rowData.add(couponData.getCoupon_type());
	        		   rowData.add(couponData.getDiscount_amount());
	        		   rowData.add(couponData.getStation_id());
	        		   rowData.add(couponData.getCoupon_status());
	        		   data.add(rowData);
	        	   }
	        	   try {
	    			eeu.exportExcel(workbook, num-1, "优惠券源数据"+num, headers, data);
	    			data.clear();//把数据写入之后清除，等待下次的数据
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
			}
        }//while结束
		try {
			workbook.write(os);
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
	@ResponseBody
	@RequestMapping("/exportCouponDataShop")
	public void exportCouponDataShop(HttpServletResponse response,Date start,Date end,String type,String coupon){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠发放与核销（种类）源数据.xls", "UTF-8");
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
		if (type==null) {
			
		}
        else if("score".equals(type)){
        	type="积分兑换";
        }else if("order".equals(type)){
        	type="活动赠送";
        }else if ("other".equals(type)) {
			type="人工赠送";
		}else{
			type=null;
		}
			
		List<String> arrayList = new ArrayList<String>();
		if("del".equals(coupon)){
			arrayList.add("燃油满减券");
			arrayList.add("非油满减券");
		}else if ("discount".equals(coupon)) {
			arrayList.add("燃油折扣券");
			arrayList.add("非油折扣券");
		}else {
			arrayList=null;
		}
		if("null".equals(type)||"".equals(type)){
			type=null;
		}
		if("null".equals(coupon)||"".equals(coupon)){
			coupon=null;
		}
		List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
        List<CouponData> list=null;
        String[] headers = { "优惠券编号", "优惠券名字","发放时间","核销时间","优惠券来源","优惠券类型","优惠券金额","核销油站编号","优惠券状态"};  
        ExportExcelUtils eeu = new ExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int st=0;
        int count=60000;
        int num=0;
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
           list = couponService.exportData2(start, end, null, type,arrayList, st, count);
    	   st+=60000;//让开始位置的加60000
    	   if(list==null||list.size()==0){
    		   break;//跳出while循环
    	   }
	    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
	    		   for (CouponData couponData : list){
	        		   List<Object>rowData = new LinkedList<Object>();
	        		   rowData.add(couponData.getID());  
	        		   rowData.add(couponData.getCoupon_title());
	        		   rowData.add(couponData.getSend_time());
	        		   rowData.add(couponData.getUsed_time());
	        		   rowData.add(couponData.getTactics_type());
	        		   rowData.add(couponData.getCoupon_type());
	        		   rowData.add(couponData.getDiscount_amount());
	        		   rowData.add(couponData.getStation_id());
	        		   rowData.add(couponData.getCoupon_status());
	        		   data.add(rowData);
	        	   }
	        	   try {
	    			eeu.exportExcel(workbook, num-1, "优惠券源数据"+num, headers, data);
	    			data.clear();//把数据写入之后清除，等待下次的数据
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
			}
        }//while结束
		try {
			workbook.write(os);
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
	@ResponseBody
	@RequestMapping("/exportCouponDataByName")
	public void exportCouponDataByName(HttpServletResponse response,Date start,Date end){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠发放与核销（活动）源数据.xls", "UTF-8");
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
		List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
        List<CouponData> list=null;
        String[] headers = { "优惠券编号", "优惠券名字","活动名称","发放时间","核销时间","优惠券来源","优惠券类型","优惠券金额","核销油站编号","优惠券状态"};  
        ExportExcelUtils eeu = new ExportExcelUtils();  
        HSSFWorkbook workbook = new HSSFWorkbook();
        int st=0;
        int count=60000;
        int num=0;
        //原理就是将所有的数据一起写入，然后再关闭输入流。  
        while (true) {//死循环
           num++;
           list = couponService.exportDataByName(start, end,st,count);
    	   st+=60000;//让开始位置的加60000
    	   if(list==null||list.size()==0){
    		   break;//跳出while循环
    	   }
	    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
	    		   for (CouponData couponData : list){
	        		   List<Object>rowData = new LinkedList<Object>();
	        		   rowData.add(couponData.getID());  
	        		   rowData.add(couponData.getCoupon_title());
	        		   rowData.add(couponData.getTactics_title());
	        		   rowData.add(couponData.getSend_time());
	        		   rowData.add(couponData.getUsed_time());
	        		   rowData.add(couponData.getTactics_type());
	        		   rowData.add(couponData.getCoupon_type());
	        		   rowData.add(couponData.getDiscount_amount());
	        		   rowData.add(couponData.getStation_id());
	        		   rowData.add(couponData.getCoupon_status());
	        		   data.add(rowData);
	        	   }
	        	   try {
	    			eeu.exportExcel(workbook, num-1, "优惠券源数据"+num, headers, data);
	    			data.clear();//把数据写入之后清除，等待下次的数据
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
			}
        }//while结束
		try {
			workbook.write(os);
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
	@ResponseBody
	@RequestMapping("/exportCouponShop")
	public void exportCouponShop(HttpServletResponse response,Date start,Date end,String date,Integer city,String type,String coupon){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠发放与核销（种类）.xls", "UTF-8");
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
		List<CouponNature> list = couponService.queryNature(city, type, coupon, start, end, date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("discount_allmoney", "折扣发放");
		titleMap.put("off_allmoney", "满减发放");
		titleMap.put("discount_usedmoney", "折扣核销");
		titleMap.put("off_usedmoney","满减核销" );
		String sheetName = "优惠券发放与核销（种类）";
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
	@ResponseBody
	@RequestMapping("/exportCouponRate")
	public void exportCouponRate(HttpServletResponse response){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠余量占比.xls", "UTF-8");
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
        List<DataPack> list = couponService.queryZhanbi();
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "优惠券");
		titleMap.put("value", "金额");
	    String sheetName = "优惠余量占比";
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
	@ResponseBody
	@RequestMapping("/queryByStation")
	public Map<String, Object> queryByStation(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,Date start,Date end,String date){
		 	List<Couponb> list = new ArrayList<Couponb>();
			if(ArryToListUtil.format(station)!=null){
				list= couponService.queryByStation(ArryToListUtil.format(station), start, end, date);
			}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
				List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
						ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
						ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
				List<String> stationid = new ArrayList<String>();
				if(queryStationBy!=null){
					for (Station station2 : queryStationBy) {
						stationid.add(station2.getId());
					}
				}
				list=couponService.queryByStation(stationid, start, end, date);
			}
			List<String> days = new ArrayList<String>();
			List<Double> oilscoreused = new ArrayList<Double>();
			List<Double> oilreissuedused = new ArrayList<Double>();
			List<Double> oilorderused = new ArrayList<Double>();
			List<Double> oilordernumused = new ArrayList<Double>();
			List<Double> oilhfiveused = new ArrayList<Double>();
			List<Double> shopScoreUsed = new ArrayList<Double>();
			List<Double> shopReissuedUsed = new ArrayList<Double>();
			List<Double> shopOrderUsed = new ArrayList<Double>();
			List<Double> shophfiveUsed = new ArrayList<Double>();
			List<Double> otherUsed = new ArrayList<Double>();
			if(list!=null&&list.size()!=0){
				for (Couponb couponb: list) {
					days.add(couponb.getDays());
					oilscoreused.add(couponb.getOil_score_usedmoney());
					oilreissuedused.add(couponb.getOil_reissued_usedmoney());
					oilorderused.add(couponb.getOil_order_usedmoney());
					oilordernumused.add(couponb.getOil_orderdis_usedmoney());
					otherUsed.add(couponb.getOil_otherdis_usedmoney());
					oilhfiveused.add(couponb.getOil_hfive_usedmoney());
					shopScoreUsed.add(couponb.getNotoil_score_usedmoney());
					shopReissuedUsed.add(couponb.getNotoil_reissued_usedmoney());
					shopOrderUsed.add(couponb.getNotoil_order_usedmoney());
					shophfiveUsed.add(couponb.getNotoil_hfive_usedmoney());
				}
			}else {
				days.add("无数据");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("days", days);
			map.put("oilscoreused",oilscoreused );//燃油积分兑换核销
			map.put("oilreissuedused",oilreissuedused );//燃油人工赠送核销
			map.put("oilorderused",oilorderused );//燃油会员活动核销
			map.put("oilordernumused",oilordernumused );//会员活动折扣核销
			map.put("oilhfiveused",oilhfiveused );//H5活动赠送核销
			map.put("shopScoreUsed",shopScoreUsed );//非油积分兑换核销
			map.put("shopReissuedUsed",shopReissuedUsed );//非油人工赠送核销
			map.put("shopOrderUsed",shopOrderUsed);//非油会员活动核销
			map.put("shophfiveUsed",shophfiveUsed);//非油H5活动核销
			map.put("otherUsed",otherUsed);//其他活动核销
			return map;
		
	}
	@ResponseBody
	@RequestMapping("/queryLadder")
	public Map<String, Object> queryLadder(@RequestParam(required=false,value="citys[]")String[] citys,
			@RequestParam(required=false,value="regions[]")String [] regions, @RequestParam(required=false,value="sales[]")String [] sales,
			@RequestParam(required=false,value="gasoline[]")String [] gasoline,
			@RequestParam(required=false,value="locs[]")String [] locs, 
			@RequestParam(required=false,value="openDate[]")String [] openDate,
			@RequestParam(required=false,value="type[]")String [] type,
			@RequestParam(required=false,value="station[]")String [] station,Date start,Date end,String date){
		 List<DataPack> list = new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			 list = couponService.queryLadder(start, end, date, ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=couponService.queryLadder(start, end, date, stationid);
		}
		List<String> days = new ArrayList<String>();
		List<Double> number = new ArrayList<Double>();
		if(list!=null&&list.size()!=0){
			for (DataPack dataPack: list) {
				days.add(dataPack.getName());
				number.add(dataPack.getValue());
			}
		}else {
			days.add("无数据");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("number",number);
		return map;
		
	}
	@ResponseBody
	@RequestMapping("/exportByStation")
	public void exportByStation(@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,HttpServletResponse response,Date start1,Date end1,String date1){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠券分油站使用情况.xls", "UTF-8");
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
        List<Couponb> list = new ArrayList<Couponb>();
        List<Couponb> list2 = new ArrayList<Couponb>();
		if(ArryToListUtil.format(station)!=null){
			list= couponService.queryByStation(ArryToListUtil.format(station), start1, end1, date1);
			list2= couponService.exportByStation(ArryToListUtil.format(station), start1, end1, date1);
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=couponService.queryByStation(stationid, start1, end1, date1);
			list2=couponService.exportByStation(stationid, start1, end1, date1);
		}
		for (Couponb couponb : list) {
			couponb.setStationID("加总");
		}
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("oil_score_usedmoney","燃油积分兑换核销" );
		titleMap.put("oil_reissued_usedmoney","燃油人工赠送核销" );
		titleMap.put("oil_order_usedmoney", "燃油会员活动核销");
		titleMap.put("oil_orderdis_usedmoney", "会员活动折扣核销");
		titleMap.put("oil_otherdis_usedmoney", "其他活动折扣核销");
		titleMap.put("oil_hfive_usedmoney","燃油H5活动赠送核销" );
		titleMap.put("notoil_score_usedmoney", "非油积分兑换核销");
		titleMap.put("notoil_reissued_usedmoney","非油人工赠送核销" );
		titleMap.put("notoil_order_usedmoney", "非油会员活动核销");
		titleMap.put("notoil_hfive_usedmoney", "非油H5活动核销");
		titleMap.put("stationID", "油站编号");
		String sheetName = "优惠券分油站使用情况";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start1,end1);
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
	@ResponseBody
	@RequestMapping("/exportLadder")
	public void exportLadder(@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,HttpServletResponse response,Date start1,Date end1,String date1){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"支付立减.xls", "UTF-8");
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
		List<DataPack> list =new ArrayList<DataPack>();
		List<DataPack> list2 =new ArrayList<DataPack>();
		if(ArryToListUtil.format(station)!=null){
			list= couponService.queryLadder(start1, end1, date1, ArryToListUtil.format(station));
			list2= couponService.exportLadder(start1, end1, date1, ArryToListUtil.format(station));
		}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
			List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
					ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
					ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
			List<String> stationid = new ArrayList<String>();
			if(queryStationBy!=null){
				for (Station station2 : queryStationBy) {
					stationid.add(station2.getId());
				}
			}
			list=couponService.queryLadder(start1, end1, date1, stationid);
			list2=couponService.exportLadder(start1, end1, date1, stationid);
		}
		for (DataPack dataPack : list) {
			dataPack.setStationID("加总");
		}
		list.addAll(list2);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "时间");
		titleMap.put("value","立减金额" );
		titleMap.put("stationID", "油站编号");
		String sheetName = "支付立减";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName,start1,end1);
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
	@ResponseBody
	@RequestMapping("/exportCouponDataByStation")
	public void exportCouponDataByStation(@RequestParam(required=false,value="citys")String[] citys,
			@RequestParam(required=false,value="regions")String [] regions, @RequestParam(required=false,value="sales")String [] sales,
			@RequestParam(required=false,value="gasolines")String [] gasoline,
			@RequestParam(required=false,value="location")String [] locs, 
			@RequestParam(required=false,value="openDate")String [] openDate,
			@RequestParam(required=false,value="type")String [] type,
			@RequestParam(required=false,value="station")String [] station,HttpServletResponse response,Date start1,Date end1,String date1){
		String encode="";
		try {
			encode = URLEncoder.encode(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"优惠分油站核销源数据.xls", "UTF-8");
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
		List<String> stationid = new ArrayList<String>();
			if(ArryToListUtil.format(station)!=null){
				stationid= ArryToListUtil.format(station);
			}else {//传过来的油站为空，因为没有选则油站，所以就按照之前的来
				List<Station> queryStationBy = stationService.queryStationBy(ArryToListUtil.format(citys), ArryToListUtil.format(regions), 
						ArryToListUtil.format(sales),ArryToListUtil.format(gasoline) , 
						ArryToListUtil.format(locs),ArryToListUtil.format(openDate),ArryToListUtil.format(type),stationService.getStationId(SecurityUtils.getSubject().getPrincipal().toString()));
				
				if(queryStationBy!=null){
					for (Station station2 : queryStationBy) {
						stationid.add(station2.getId());
					}
				}
			}
			List<List<Object>> data = new LinkedList<List<Object>>();//存放数据的
	        List<CouponData> list=null;
	        String[] headers = { "优惠券编号", "优惠券名字","发放时间","核销时间","优惠券来源","优惠券类型","优惠券金额","核销油站编号","优惠券状态"};  
	        ExportExcelUtils eeu = new ExportExcelUtils();  
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        int st=0;
	        int count=60000;
	        int num=0;
	        //原理就是将所有的数据一起写入，然后再关闭输入流。  
	        while (true) {//死循环
	           num++;
	           list = couponService.exportData(start1, end1, stationid, null, null,st,count);
	    	   st+=60000;//让开始位置的加60000
	    	   if(list==null||list.size()==0){
	    		   break;//跳出while循环
	    	   }
		    	   if(list!=null&&list.size()!=0) {//这是证明新查询出来的list不为空,如果为空不会进行，跳到开始，然后条件不符合，就跳出整个大的while循环
		    		   for (CouponData couponData : list){
		        		   List<Object>rowData = new LinkedList<Object>();
		        		   rowData.add(couponData.getID());  
		        		   rowData.add(couponData.getCoupon_title());
		        		   rowData.add(couponData.getSend_time());
		        		   rowData.add(couponData.getUsed_time());
		        		   rowData.add(couponData.getTactics_type());
		        		   rowData.add(couponData.getCoupon_type());
		        		   rowData.add(couponData.getDiscount_amount());
		        		   rowData.add(couponData.getStation_id());
		        		   rowData.add(couponData.getCoupon_status());
		        		   data.add(rowData);
		        	   }
		        	   try {
		    			eeu.exportExcel(workbook, num-1, "优惠券源数据"+num, headers, data);
		    			data.clear();//把数据写入之后清除，等待下次的数据
		    			} catch (Exception e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}  
				}
	        }//while结束
			try {
				workbook.write(os);
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
	@RequestMapping("/queryAlls")
	@ResponseBody
	public Map<String, Object> queryAlls(String date,Date start,Date end){
		List<CouponAll> list = couponService.queryCouponAlls(start, end, date);
		List<String> days = new ArrayList<>();
		List<Integer> oilGive = new ArrayList<>();
		List<Integer> oilUsed = new ArrayList<>();
		List<Integer> shopGive = new ArrayList<>();
		List<Integer> shopUsed = new ArrayList<>();
		List<Integer> scoreGive = new ArrayList<>();
		List<Integer> scoreUsed = new ArrayList<>();
		List<Integer> orderUsed = new ArrayList<>();
		List<Integer> orderGive= new ArrayList<>();
		for (CouponAll couponAll : list) {
			days.add(couponAll.getDays());
			oilGive.add(couponAll.getOil_allmoney());
			oilUsed.add(couponAll.getOil_usedmoney());
			shopGive.add(couponAll.getNotoil_allmoney());
			shopUsed.add(couponAll.getNotoil_usedmoney());
			scoreGive.add(couponAll.getScore_allmoney());
			scoreUsed.add(couponAll.getScore_usedmoney());
			orderGive.add(couponAll.getOrder_allmoney());
			orderUsed.add(couponAll.getOrder_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("oilGive", oilGive);
		map.put("oilUsed", oilUsed);
		map.put("shopGive", shopGive);
		map.put("shopUsed", shopUsed);
		map.put("scoreGive",scoreGive );
		map.put("scoreUsed", scoreUsed);
		map.put("orderGive", orderGive);
		map.put("orderUsed", orderUsed);
		return map;
	}
	@RequestMapping("/queryCouponOil")
	@ResponseBody
	public Map<String, Object> queryCouponOil(String date,Date start,Date end){
		List<String> days = new ArrayList<>();
		List<Integer> discountGive = new ArrayList<>();
		List<Integer> discountUsed = new ArrayList<>();
		List<Integer> reductionGive = new ArrayList<>();
		List<Integer> reductionUsed = new ArrayList<>();
		List<Integer> givingGive = new ArrayList<>();
		List<Integer> givingUsed = new ArrayList<>();
		List<CouponByType> list = couponService.queryCouponOil(start, end, date);
		for (CouponByType couponByType : list) {
			days.add(couponByType.getDays());
			discountGive.add(couponByType.getDiscount_allmoney());
			discountUsed.add(couponByType.getDiscount_usedmoney());
			reductionGive.add(couponByType.getReduction_allmoney());
			reductionUsed.add(couponByType.getReduction_usedmoney());
			givingGive.add(couponByType.getGiving_allmoney());
			givingUsed.add(couponByType.getGiving_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("discountGive", discountGive);
		map.put("discountUsed",discountUsed );
		map.put("reductionGive",reductionGive );
		map.put("reductionUsed", reductionUsed);
		map.put("givingGive", givingGive);
		map.put("givingUsed", givingUsed);
		return map;
	}
	@RequestMapping("/queryCouponShop")
	@ResponseBody
	public Map<String, Object> queryCouponShop(String date,Date start,Date end){
		List<CouponByType> list = couponService.queryCouponShop(start, end, date);
		List<String> days = new ArrayList<>();
		List<Integer> discountGive = new ArrayList<>();
		List<Integer> discountUsed = new ArrayList<>();
		List<Integer> reductionGive = new ArrayList<>();
		List<Integer> reductionUsed = new ArrayList<>();
		List<Integer> givingGive = new ArrayList<>();
		List<Integer> givingUsed = new ArrayList<>();
		for (CouponByType couponByType : list) {
			days.add(couponByType.getDays());
			discountGive.add(couponByType.getDiscount_allmoney());
			discountUsed.add(couponByType.getDiscount_usedmoney());
			reductionGive.add(couponByType.getReduction_allmoney());
			reductionUsed.add(couponByType.getReduction_usedmoney());
			givingGive.add(couponByType.getGiving_allmoney());
			givingUsed.add(couponByType.getGiving_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("discountGive", discountGive);
		map.put("discountUsed",discountUsed );
		map.put("reductionGive",reductionGive );
		map.put("reductionUsed", reductionUsed);
		map.put("givingGive", givingGive);
		map.put("givingUsed", givingUsed);
		return map;
	}
}
