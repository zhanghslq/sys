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

import com.yb.entity.AddVip;
import com.yb.entity.Couponb;
import com.yb.entity.DataPack;
import com.yb.entity.Evaluation;
import com.yb.entity.NotOil;
import com.yb.entity.Oil;
import com.yb.entity.Recharge;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.AddVipService;
import com.yb.service.CouponService;
import com.yb.service.EvaluationService;
import com.yb.service.NotOilService;
import com.yb.service.OilService;
import com.yb.service.RechargeService;
import com.yb.util.DateFormatUtils;
import com.yb.util.DoubleFormatUtil;

@Controller
@RequestMapping("/addVip")
@Scope("prototype")
public class AddVipController {
	//全网新增会员
	@Resource
	private AddVipService addVipService;
	@Resource
	private OilService oilService;
	@Resource
	private NotOilService notOilService;
	@Resource
	private RechargeService rechargeService;
	@Resource
	private EvaluationService evaluationService;
	@Resource
	private CouponService couponService;
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, List> query(String date,Date start,Date end,String area){
		List<AddVip> list = addVipService.query(date, start, end,area);
		List<String> dates = new ArrayList<String>();
		List<Integer> addNumbers = new ArrayList<Integer>();
		List<Integer> totalPeoples = new ArrayList<Integer>();
		for (AddVip addVip : list) {
			dates.add(addVip.getDate());
			addNumbers.add(addVip.getNumber());
			totalPeoples.add(addVip.getTotalPeople());
		}
		HashMap<String,List> map = new HashMap<String,List>();
		map.put("dates", dates);
		map.put("addNumbers", addNumbers);
		map.put("totalPeoples", totalPeoples);
		return map;
	}
	@ResponseBody
	@RequestMapping("/exportAddVip")
	public void exportOils(String date,Date start,Date end,String area,HttpServletResponse response){
		String encode="";
		String abc="";
		if("BJSHELL".equals(area)){
			abc="北京";
		}
		if("CDSHELL".equals(area)){
			abc="承德";
		}
		try {
			encode = URLEncoder.encode(abc+"会员招募.xls", "UTF-8");
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
        List<AddVip> list = addVipService.query(date, start, end,area);
		
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("date", "时间");
		titleMap.put("number", "招募人数");
		titleMap.put("totalPeople", "会员总数");
		String sheetName = "会员招募信息";
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
	//dashboard会员情况
	//现有会员，当日新增，本月累计活跃会员and占比,本月累计新增会员
	//会员近七天油品销量，近七天便利店销售额
	//会员当日油品交易额占比，便利店交易额占比
	//本月累计油品交易额占比，便利店交易额占比
	//本月累计油品优惠券核销率，便利店优惠券核销率，（暂无）
	//当日充值，本月充值，今年累计充值
	//评分系统，当日评分，当月评分，评价条数
	@RequestMapping("/queryDashBoard")
	@ResponseBody
	public Map<String, Object> queryDashBoard(){
		Date date = new Date();
		Integer vipnow=null;//现有会员人数
		Integer addDay=0;//当日新增
		Integer addMonth=0;//本月新增
		Integer activeInteger=0;//活跃会员人数
		String activity="0%";//活跃人数占比
		List<String> oilDates=new ArrayList<String>();
		List<String> shopDates=new ArrayList<String>();
		List<Double> oilDatas=new ArrayList<Double>();
		List<Double> shopDatas=new ArrayList<Double>();
		List<DataPack> dayVipOil=new ArrayList<DataPack>();//日占比
		List<DataPack> dayVipShop=new ArrayList<DataPack>();//日占比
		List<DataPack> monthVipOil=new ArrayList<DataPack>();//月占比
		List<DataPack> monthVipShop=new ArrayList<DataPack>();//月占比
		Integer queryTotal = addVipService.queryTotal("CDSHELL");
		Integer queryTotal2 = addVipService.queryTotal("BJSHELL");
		vipnow=queryTotal+queryTotal2;//北京承德相加
		List<AddVip> dayBj = addVipService.query("day", date, date, "BJSHELL");
		if(dayBj!=null&&dayBj.size()!=0){
			for (AddVip addVip : dayBj) {
				addDay=addVip.getNumber();//新招募会员
			}
		}
		List<AddVip> monthBj = addVipService.query("month", DateFormatUtils.getMonthStart(), date, "BJSHELL");
		if(monthBj!=null&&monthBj.size()!=0){
			for (AddVip addVip : monthBj) {
				addMonth = addVip.getNumber();
			}
		}
		List<AddVip> monthCD = addVipService.query("month", DateFormatUtils.getMonthStart(), date, "CDSHELL");//承德当月新增
		if(monthCD!=null&&monthCD.size()!=0){
			for (AddVip addVip : monthCD) {
				addMonth+=addVip.getNumber();
			}
		}
		List<AddVip> dayCD = addVipService.query("day", date, date, "CDSHELL");
		if(dayCD!=null&&dayCD.size()!=0){
			for (AddVip addVip : dayCD) {
				addDay+=addVip.getNumber();
			}
		}
		Integer queryActive = addVipService.queryActive("BJSHELL");
		Integer queryActive2 = addVipService.queryActive("CDSHELL");
		activeInteger=queryActive+queryActive2;
		activity=DoubleFormatUtil.format(Double.valueOf(String.valueOf(activeInteger*100/vipnow)))+"%";
		//会员七天油品的交易额
		List<Oil> queryOils = oilService.queryOils("day", DateFormatUtils.getWeekStart(), new Date(), null, "vip");
		for (Oil oil : queryOils) {
			oilDates.add(oil.getMinutes());
			oilDatas.add(oil.getOilLitre());
		}
		//会员七天便利店的消费
		List<NotOil> queryNotOils = notOilService.queryNotOils("day", DateFormatUtils.getWeekStart(), new Date(), null, "vip");
		for (NotOil notOil : queryNotOils) {
			shopDates.add(notOil.getMinutes());
			shopDatas.add(notOil.getNotOilMoney());
		}
		//当日占比
		Double allOilDaySalesDouble=0.0;
		Double vipOilDaySales=0.0;
		Double allOilMonthSales=0.0;
		Double vipOilMonthSalesDouble=0.0;
		Double allShopDaySalesDouble=0.0;
		Double vipShopDaySales=0.0;
		Double allShopMonthSales=0.0;
		Double vipShopMonthSalesDouble=0.0;
		//油品的会员非会员的数据
		List<Oil> allOilDay = oilService.queryOils("day", DateFormatUtils.getDayStart(), new Date(), null, "all");
		if(allOilDay!=null&&allOilDay.size()!=0){
			for (Oil oil : allOilDay) {
				allOilDaySalesDouble=oil.getOilLitre();
			}
		}
		List<Oil> vipOilDay = oilService.queryOils("day", DateFormatUtils.getDayStart(), new Date(), null, "vip");
		if(vipOilDay!=null&&vipOilDay.size()!=0){
			for (Oil oil : vipOilDay) {
				vipOilDaySales=oil.getOilLitre();
			}
		}
		List<Oil> allOilMonth = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), null, "all");
		if(allOilMonth!=null&&allOilMonth.size()!=0){
			for (Oil oil : allOilMonth) {
				allOilMonthSales=oil.getOilLitre();
			}
		}
		List<Oil> vipOilMonth = oilService.queryOils("month", DateFormatUtils.getMonthStart(), new Date(), null, "vip");
		if(vipOilMonth!=null&&vipOilMonth.size()!=0){
			for (Oil oil : vipOilMonth) {
				vipOilMonthSalesDouble=oil.getOilLitre();
			}
		}
		//便利店的会员非会员的当天的，当月的数据
		List<NotOil> allShopDay = notOilService.queryNotOils("day", DateFormatUtils.getDayStart(), new Date(), null, "all");
		if(allShopDay!=null&&allShopDay.size()!=0){
			for (NotOil notOil : allShopDay) {
				allShopDaySalesDouble=notOil.getNotOilMoney();
			}
		}
		List<NotOil> allShopMonth = notOilService.queryNotOils("month", DateFormatUtils.getDayStart(), new Date(), null, "all");
		if(allShopMonth!=null&&allShopMonth.size()!=0){
			for (NotOil notOil : allShopMonth) {
				allShopMonthSales=notOil.getNotOilMoney();
			}
		}
		List<NotOil> vipShopDay = notOilService.queryNotOils("day", DateFormatUtils.getDayStart(), new Date(), null, "vip");
		if(vipShopDay!=null&&vipShopDay.size()!=0){
			for (NotOil notOil : vipShopDay) {
				vipShopDaySales=notOil.getNotOilMoney();
			}
		}
		List<NotOil> vipShopMonth = notOilService.queryNotOils("month", DateFormatUtils.getDayStart(), new Date(), null, "vip");
		if(vipShopMonth!=null&&vipShopMonth.size()!=0){
			for (NotOil notOil : vipShopMonth) {
				vipShopMonthSalesDouble=notOil.getNotOilMoney();
			}
		}
		dayVipOil.add(new DataPack("当日会员油品交易量", vipOilDaySales));//当天的油品会员占比
		dayVipOil.add(new DataPack("当日非会员油品交易量", allOilDaySalesDouble-vipOilDaySales));//总的减去会员的就是非会员的
		dayVipShop.add(new DataPack("当日会员便利店交易额", vipShopDaySales));
		dayVipShop.add(new DataPack("当日非会员便利店交易额", allShopDaySalesDouble-vipShopDaySales));
		monthVipOil.add(new DataPack("本月会员累计油品交易量", vipOilMonthSalesDouble));
		monthVipOil.add(new DataPack("本月非会员累计油品交易量", allOilMonthSales-vipOilMonthSalesDouble));
		monthVipShop.add(new DataPack("本月会员便利店交易额", vipShopMonthSalesDouble));
		monthVipShop.add(new DataPack("本月非会员便利店交易额",allShopMonthSales-vipShopMonthSalesDouble));
		Double dayRecharge=0.0;
		Double monthRecharge=0.0;
		Double yearRecharge=0.0;
		List<Recharge> bjDay = rechargeService.query("day", DateFormatUtils.getDayStart(), new Date(), "BJSHELL");
		if(bjDay!=null&&bjDay.size()!=0){
			for (Recharge recharge : bjDay) {
				dayRecharge+=recharge.getTotalAmount();
			}
		}
		List<Recharge> cdDay = rechargeService.query("day", DateFormatUtils.getDayStart(), new Date(), "CDSHELL");
		if(cdDay!=null&&cdDay.size()!=0){
			for (Recharge recharge : cdDay) {
				dayRecharge+=recharge.getTotalAmount();
			}
		}
		List<Recharge> bjMonth = rechargeService.query("month", DateFormatUtils.getMonthStart(), new Date(), "BJSHELL");
		if(bjMonth!=null&&bjMonth.size()!=0){
			for (Recharge recharge : bjMonth) {
				monthRecharge+=recharge.getTotalAmount();
			}
		}
		List<Recharge> cdMonth = rechargeService.query("month",DateFormatUtils.getMonthStart(), new Date(), "CDSHELL");
		if(cdMonth!=null&&cdMonth.size()!=0){
			for (Recharge recharge : cdMonth) {
				monthRecharge+=recharge.getTotalAmount();
			}
		}
		List<Recharge> bjYear = rechargeService.query("year", DateFormatUtils.getYearStart(), new Date(), "BJSHELL");
		if(bjYear!=null&&bjYear.size()!=0){
			for (Recharge recharge : bjYear) {
				yearRecharge+=recharge.getTotalAmount();
			}
		}
		List<Recharge> cdYear = rechargeService.query("year", DateFormatUtils.getYearStart(), new Date(), "CDSHELL");
		if(cdYear!=null&&cdYear.size()!=0){
			for (Recharge recharge : cdYear) {
				yearRecharge+=recharge.getTotalAmount();
			}
		}
		Double dayStar=5.0;
		Double monthStar=5.0;
		Double dayAmount=0.0;
		Double monthAmount=0.0;
		List<Evaluation> queryTrend = evaluationService.queryTrend("day", DateFormatUtils.getDayStart(), new Date(), null);
		if(queryTrend!=null&&queryTrend.size()!=0){
			for (Evaluation evaluation : queryTrend) {
				dayStar = DoubleFormatUtil.format(evaluation.getStar1());
				dayAmount=evaluation.getStar2();
			}
		}
		List<Evaluation> queryTrend2 = evaluationService.queryTrend("month", DateFormatUtils.getMonthStart(), new Date(), null);
		if(queryTrend2!=null&&queryTrend2.size()!=0){
			for (Evaluation evaluation : queryTrend2) {
				monthStar=DoubleFormatUtil.format(evaluation.getStar1());
				monthAmount=evaluation.getStar2();
			}
		}
		Integer oilCoupon=0;
		Integer oilCouponused=0;
		Integer shopCoupon=0;
		Integer shopCouponused=0;
		List<Couponb> list = couponService.queryByType(DateFormatUtils.getMonthStart(), new Date(), "month");
		if(list!=null&&list.size()!=0){
			for (Couponb couponb : list) {
				oilCoupon+=couponb.getOil_gived_all();
				oilCoupon+=couponb.getNotoil_score_all();
				oilCouponused+=couponb.getOil_gived_used();
				oilCouponused+=couponb.getOil_score_used();
				shopCoupon+=couponb.getNotoil_gived_all();
				shopCoupon+=couponb.getNotoil_score_all();
				shopCouponused+=couponb.getNotoil_gived_used();
				shopCouponused+=couponb.getNotoil_score_used();
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vipnow", vipnow);//现有会员人数
		map.put("activity", activity);
		map.put("activeInteger", activeInteger);
		map.put("addDay", addDay);
		map.put("addMonth", addMonth);
		map.put("oilDates", oilDates);
		map.put("oilDatas", oilDatas);
		map.put("shopDates", shopDates);
		map.put("shopDatas", shopDatas);
		map.put("dayVipOil", dayVipOil);
		map.put("dayVipShop", dayVipShop);
		map.put("monthVipOil", monthVipOil);
		map.put("monthVipShop", monthVipShop);
		map.put("dayRecharge", dayRecharge);
		map.put("monthRecharge", monthRecharge);
		map.put("yearRecharge", yearRecharge);
		map.put("dayStar", dayStar);
		map.put("monthStar", monthStar);
		map.put("monthAmount", monthAmount);
		map.put("dayAmount", dayAmount);
		map.put("oilRate", DoubleFormatUtil.format(Double.valueOf(String.valueOf(oilCouponused))/oilCoupon)*100+"%");
		map.put("shopRate", DoubleFormatUtil.format(Double.valueOf(String.valueOf(shopCouponused))/shopCoupon)*100+"%");
		return map;
	}
}
