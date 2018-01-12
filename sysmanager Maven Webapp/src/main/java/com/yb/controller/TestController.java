package com.yb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yb.excel.use.Employee;
import com.yb.excel.util.ExportExcelUtil;

@RequestMapping("/test")
@Controller
public class TestController {
	@RequestMapping("/test")
	public void test(HttpServletResponse response){
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
		Map<String,String> titleMap = new LinkedHashMap<String,String>();
		titleMap.put("name", "姓名");
		titleMap.put("clazz", "clazz");
		titleMap.put("year", "year");
		titleMap.put("month", "month");
		titleMap.put("day", "day");
		titleMap.put("salary", "salary");
		String sheetName = "会员信息导出";
		LinkedList<Employee> list = new LinkedList<Employee>();
		for (int i = 0; i < 10000; i++) {
			list.add(new Employee(i, i+"", i, i, i, i));
		}
		//应该是要返回一个hsswork然后os响应出来
				try {
					ExportExcelUtil.excelExport(list, titleMap, sheetName).write(os);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        try {
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
	}
}
