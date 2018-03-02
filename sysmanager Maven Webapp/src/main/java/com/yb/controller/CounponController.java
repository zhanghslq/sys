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
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.CouponAll;
import com.yb.entity.CouponByType;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;
import com.yb.entity.Station;
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
			encode = URLEncoder.encode("优惠发放与核销（整体）.xls", "UTF-8");
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
        List<CouponAll> list = couponService.queryCouponAlls(start, end, date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("oil_allmoney", "燃油发放");
		titleMap.put("notoil_allmoney", "非油发放");
		titleMap.put("score_allmoney","积分兑换" );
		titleMap.put("oil_usedmoney", "燃油核销");
		titleMap.put("notoil_usedmoney","非油核销" );
		titleMap.put("score_usedmoney","积分兑换核销" );
		String sheetName = "优惠券发放与核销（整体）";
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
	@RequestMapping("/exportCouponOil")
	public void exportCouponOil(HttpServletResponse response,Date start,Date end,String query,String area,String date){
		String encode="";
		try {
			encode = URLEncoder.encode("优惠发放与核销（燃油）.xls", "UTF-8");
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
        List<CouponByType> list = couponService.queryCouponOil(start, end, date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("discount_allmoney", "折扣发放");
		titleMap.put("reduction_allmoney", "立减发放");
		titleMap.put("giving_allmoney","赠送" );
		titleMap.put("discount_usedmoney", "折扣核销");
		titleMap.put("reduction_usedmoney","立减核销" );
		titleMap.put("giving_usedmoney","赠送核销" );
		String sheetName = "优惠券发放与核销（燃油）";
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
	@RequestMapping("/exportCouponShop")
	public void exportCouponShop(HttpServletResponse response,Date start,Date end,String query,String area,String date){
		String encode="";
		try {
			encode = URLEncoder.encode("优惠发放与核销（便利店）.xls", "UTF-8");
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
		List<CouponByType> list = couponService.queryCouponShop(start, end, date);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("days", "时间");
		titleMap.put("discount_allmoney", "折扣发放");
		titleMap.put("reduction_allmoney", "立减发放");
		titleMap.put("giving_allmoney","赠送" );
		titleMap.put("discount_usedmoney", "折扣核销");
		titleMap.put("reduction_usedmoney","立减核销" );
		titleMap.put("giving_usedmoney","赠送核销" );
		String sheetName = "优惠券发放与核销（便利店）";
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
	@ResponseBody
	@RequestMapping("/exportCouponRate")
	public void exportCouponRate(HttpServletResponse response,Date start,Date end,String query,String area,String date){
		String encode="";
		try {
			encode = URLEncoder.encode("优惠余量占比.xls", "UTF-8");
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
			if(list!=null&&list.size()!=0){
				for (Couponb couponb: list) {
					days.add(couponb.getDays());
					oilscoreused.add(couponb.getOil_score_allmoney());
					oilreissuedused.add(couponb.getOil_reissued_usedmoney());
					oilorderused.add(couponb.getOil_order_usedmoney());
					oilordernumused.add(couponb.getOil_order_usednum());
					oilhfiveused.add(couponb.getOil_hfive_usedmoney());
					shopScoreUsed.add(couponb.getNotoil_score_usedmoney());
					shopReissuedUsed.add(couponb.getNotoil_reissued_allmoney());
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
			encode = URLEncoder.encode("优惠券分油站使用情况.xls", "UTF-8");
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
		titleMap.put("oil_order_usednum", "会员活动折扣核销(笔数)");
		titleMap.put("oil_hfive_usedmoney","燃油H5活动赠送核销" );
		titleMap.put("notoil_score_usedmoney", "非油积分兑换核销");
		titleMap.put("notoil_reissued_usedmoney","非油人工赠送核销" );
		titleMap.put("notoil_order_usedmoney", "非油会员活动核销");
		titleMap.put("notoil_hfive_usedmoney", "非油H5活动核销");
		titleMap.put("stationID", "油站编号");
		String sheetName = "优惠券分油站使用情况";
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
		for (CouponAll couponAll : list) {
			days.add(couponAll.getDays());
			oilGive.add(couponAll.getOil_allmoney());
			oilUsed.add(couponAll.getOil_usedmoney());
			shopGive.add(couponAll.getNotoil_allmoney());
			shopUsed.add(couponAll.getNotoil_usedmoney());
			scoreGive.add(couponAll.getScore_allmoney());
			scoreUsed.add(couponAll.getScore_usedmoney());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("days", days);
		map.put("oilGive", oilGive);
		map.put("oilUsed", oilUsed);
		map.put("shopGive", shopGive);
		map.put("shopUsed", shopUsed);
		map.put("scoreGive",scoreGive );
		map.put("scoreUsed", scoreUsed);
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
