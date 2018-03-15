package com.yb.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yb.entity.TagActive;
import com.yb.entity.VipTag;
import com.yb.excel.read.ExcelOperate;
import com.yb.service.TagActiveService;

@Controller
@RequestMapping("/tagActive")
@Scope("prototype")
public class TagActiveController {
	@Resource
	private TagActiveService tagActiveService;
	@RequestMapping("/add")
	@ResponseBody
	public  void importData(MultipartFile multipartFile,HttpServletRequest request,Integer id){
		try {
	        CommonsMultipartFile cf= (CommonsMultipartFile)multipartFile; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); //应该是创建临时文件
	        File file = fi.getStoreLocation();
			String[][] result = ExcelOperate.getData(file, 1);
	      int rowLength = result.length;
	      List<VipTag> list = new LinkedList<VipTag>();
	      for(int i=0;i<rowLength;i++) {
	    	  list.add(new VipTag());
			for(int j=0;j<result[i].length;j++) {
			switch (j) {
				case 0:
					list.get(i).setCarduser_id(result[i][j]);
					break;
				case 1:
					list.get(i).setName(result[i][j]);
					break;
				case 2:
					list.get(i).setMobilePhone(result[i][j]);
					break;
				default:
					break;
				}
			   }//内部遍历
      }//外部循环
      tagActiveService.insertVipTag(list, id);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/insert")
	public void insert(TagActive tagActive){
		try {
			tagActiveService.insertTag(tagActive);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("delete")
	public void delete(Integer id){
		try {
			tagActiveService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/queryAll")
	public List<TagActive> queryAll(){//查出来所有的活动标签
		List<TagActive> list = tagActiveService.queryAll();
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryByArea")
	public List<TagActive> queryByArea(String area){//查出来所有的活动标签
		if(area==null){
			area="BJSHELL";
		}
		List<TagActive> list = tagActiveService.queryByArea(area);
		return list;
	}
}
