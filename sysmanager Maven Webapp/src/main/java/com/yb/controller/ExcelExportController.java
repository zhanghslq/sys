package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yb.entity.VipTag;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.excel.util.ExportExcelUtil;
import com.yb.service.VipTagService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/excelExport")
public class ExcelExportController {
	private Logger logger = Logger.getLogger(ExcelExportController.class.getName());
	 //设定配置文件的位置，如果不设置则要把配置文件放到class目录或根目录
	   /*PropertyConfigurator.configure(".log4j.properties");   */
	@Resource
	private VipTagService vipTagService;
	@RequestMapping("/exportVip")
	@ResponseBody
	public void export(HttpServletResponse response,@RequestParam(required=false,value="loyalty")String[] loyalty,@RequestParam(required=false,value="identity")String[] identity,
			@RequestParam(required=false,value="gender")String[] gender,@RequestParam(required=false,value="age")String[] age,
			@RequestParam(required=false,value="type")String[] type,@RequestParam(required=false,value="coupon")String[] coupon,
			@RequestParam(required=false,value="recentOil")String[] recentOil,@RequestParam(required=false,value="recentNotOil")String[] recentNotOil,
			@RequestParam(required=false,value="shortOil")String[] shortOil,Integer page,Integer rows,
			@RequestParam(required=false,value="mopType")String[] mopType,@RequestParam(required=false,value="oilName")String[] oilName,
			@RequestParam(required=false,value="shopName")String[] shopName,@RequestParam(required=false,value="station")String[] station){
		String encode = null;
		try {
			encode = URLEncoder.encode("会员信息导出.xlsx", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        OutputStream os = null;
		try {
			os = new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        List<VipTag> list = vipTagService.query(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil),ArryToListUtil.format(station),ArryToListUtil.format(oilName),
				ArryToListUtil.format(shopName),ArryToListUtil.format(mopType),0,500000);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "姓名");
		titleMap.put("carduser_id", "编号");
		titleMap.put("mobilePhone", "手机号");
		String sheetName = "会员信息导出";
		logger.error("准备导出，记录，未出错");
		//应该是要返回一个hsswork然后os响应出来
		if(list.size()>60000){
			try {
				System.out.println("大于六万条");
				ExportExcelUtil.excelExport(list, titleMap, sheetName).write(os);
				System.out.println("响应结束");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}else {
			try {
				EchartsExportExcelUtil.excelExport(list, titleMap, sheetName).write(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
        try {
        	logger.error("关闭响应流");
			os.flush();
			os.close();
			logger.error("关闭响应流结束");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("关闭响应流");
			e.printStackTrace();
		}  
        
	}
}
