package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yb.entity.VipTag;
import com.yb.excel.util.EchartsExportExcelUtil;
import com.yb.service.VipTagService;
import com.yb.util.ArryToListUtil;

@Controller
@Scope("prototype")
@RequestMapping("/excelExport")
public class ExcelExportController {
	@Resource
	private VipTagService vipTagService;
	@RequestMapping("/test")
	public void export(HttpServletResponse response,@RequestParam(required=false,value="loyalty")String[] loyalty,@RequestParam(required=false,value="identity")String[] identity,
			@RequestParam(required=false,value="gender")String[] gender,@RequestParam(required=false,value="age")String[] age,
			@RequestParam(required=false,value="type")String[] type,@RequestParam(required=false,value="coupon")String[] coupon,
			@RequestParam(required=false,value="recentOil")String[] recentOil,@RequestParam(required=false,value="recentNotOil")String[] recentNotOil,
			@RequestParam(required=false,value="shortOil")String[] shortOil,Integer page,Integer rows,
			@RequestParam(required=false,value="mopType")String[] mopType,@RequestParam(required=false,value="oilName")String[] oilName,
			@RequestParam(required=false,value="shopName")String[] shopName,@RequestParam(required=false,value="station")String[] station) throws IOException{
		String encode = URLEncoder.encode("会员信息导出.xlsx", "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes(),"UTF-8"));  
        OutputStream os= new BufferedOutputStream(response.getOutputStream());  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        
        List<VipTag> list = vipTagService.query(ArryToListUtil.format(loyalty),ArryToListUtil.format(identity) ,ArryToListUtil.format(gender) ,
				ArryToListUtil.format(age),ArryToListUtil.format(type) , 
				ArryToListUtil.format(coupon), ArryToListUtil.format(recentOil), ArryToListUtil.format(recentNotOil),
				ArryToListUtil.format(shortOil),ArryToListUtil.format(station),ArryToListUtil.format(oilName),
				ArryToListUtil.format(shopName),ArryToListUtil.format(mopType),0,65500);
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "姓名");
		titleMap.put("carduser_id", "编号");
		titleMap.put("mobilePhone", "手机号");
		String sheetName = "会员信息导出";
		//应该是要返回一个hsswork然后os响应出来
		HSSFWorkbook excelExport = EchartsExportExcelUtil.excelExport(list, titleMap, sheetName);
		excelExport.write(os);
        try {
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
	}
}
