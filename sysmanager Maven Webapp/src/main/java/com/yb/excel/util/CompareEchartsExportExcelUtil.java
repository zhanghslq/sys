package com.yb.excel.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/***
 * 导出List<Object>数据到excel（最多可导出65535行）
 * @author user
 */
public final class CompareEchartsExportExcelUtil {

  /***
   * 构造方法
   */
  private CompareEchartsExportExcelUtil() {

  }

  /***
   * 工作簿
   */
  private static HSSFWorkbook workbook;

  /***
   * sheet
   */
  private static HSSFSheet sheet;
 /***
   * 标题行开始位置
   */
  private static final int TITLE_START_POSITION = 0;

  /***
   * 时间行开始位置
   */
  private static final int DATEHEAD_START_POSITION = 1;

  /***
   * 表头行开始位置
   */
  private static final int HEAD_START_POSITION = 2;

  /***
   * 文本行开始位置
   */
  private static final int CONTENT_START_POSITION = 3;
  /**
   * 
   * @param dataList
   *        对象集合
   * @param titleMap
   *        表头信息（对象属性名称->要显示的标题值)[按顺序添加]
   * @param sheetName
   *        sheet名称和表头值
   */
  public static HSSFWorkbook excelExport(List<?> dataList, Map<String, String> titleMap, String sheetName,Date oldstart,Date oldend,Date newstart,Date newend) {
    // 初始化workbook
    initHSSFWorkbook(sheetName);
    // 标题行
    createTitleRow(titleMap, sheetName);
    // 时间行
    createDateHeadRow(titleMap,oldstart,oldend,newstart,newend);
    // 表头行
    createHeadRow(titleMap);
    // 文本行
    createContentRow(dataList, titleMap);
    //设置自动伸缩
    //autoSizeColumn(titleMap.size());
    // 写入处理结果
    try {//导出Excel，返回的是Excel的文件流
      //生成UUID文件名称
     /* String filedisplay = fileName + ".xls";*/
      //如果web项目，1、设置下载框的弹出（设置response相关参数)；2、通过httpservletresponse.getOutputStream()获取
      return workbook;
      /*OutputStream out = new FileOutputStream(dirctory+"\\"+filedisplay);
      workbook.write(out);
      out.close();*/
    }
    catch (Exception e) {
      e.printStackTrace();
    }
	return workbook;
  }

  /***
   * 
   * @param sheetName
   *        sheetName
   */
  private static void initHSSFWorkbook(String sheetName) {
    workbook = new HSSFWorkbook();
    sheet = workbook.createSheet(sheetName);
  }

  /**
   * 生成标题（第零行创建）
   * @param titleMap 对象属性名称->表头显示名称
   * @param sheetName sheet名称
   */
  private static void createTitleRow(Map<String, String> titleMap, String sheetName) {
    CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, titleMap.size() - 1);
    sheet.addMergedRegion(titleRange);
    HSSFRow titleRow = sheet.createRow(TITLE_START_POSITION);
    HSSFCell titleCell = titleRow.createCell(0);
    titleCell.setCellValue(sheetName);
  }

  /**
   * 创建时间行（第一行创建）
   * @param titleMap 对象属性名称->表头显示名称
   */
  private static void createDateHeadRow(Map<String, String> titleMap,Date oldstart,Date oldend,Date newstart,Date newend) {
    CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, titleMap.size() - 1);
    sheet.addMergedRegion(dateRange);
    HSSFRow dateRow = sheet.createRow(DATEHEAD_START_POSITION);
    HSSFCell dateCell = dateRow.createCell(0);   
    dateCell.setCellValue(new SimpleDateFormat("yyyy年MM月dd日").format(oldstart)+"-"+new SimpleDateFormat("yyyy年MM月dd日").format(oldend)+"对比"+
    		new SimpleDateFormat("yyyy年MM月dd日").format(newstart)+"-"+new SimpleDateFormat("yyyy年MM月dd日").format(newend) );
  }

  /**
   * 创建表头行（第二行创建）
   * @param titleMap 对象属性名称->表头显示名称
   */
  private static void createHeadRow(Map<String, String> titleMap) {
    // 第1行创建
    HSSFRow headRow = sheet.createRow(HEAD_START_POSITION);
    int i = 0;
    for (String entry : titleMap.keySet()) {
      HSSFCell headCell = headRow.createCell(i);
      headCell.setCellValue(titleMap.get(entry));
      i++;
    }
  }

 /**
  * 
  * @param dataList 对象数据集合
  * @param titleMap 表头信息
  */
  private static  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
private static void createContentRow(List<?> dataList, Map<String, String> titleMap) {
    try {
      int i=0;
      for (Object obj : dataList) {
        HSSFRow textRow = sheet.createRow(CONTENT_START_POSITION + i);
        int j = 0;
        for (String entry : titleMap.keySet()) {
        	HSSFCell textcell = textRow.createCell(j);
        	    try {
					String method = "get" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
					
					Field field = obj.getClass().getDeclaredField(entry);
					//判断属性类型
					Method m = obj.getClass().getMethod(method, null);//获取方法
					Object value = m.invoke(obj, null);//方法执行// 调用getter方法获取属性值  
					if(value!=null){
						if(field.getGenericType().toString().equals("class java.lang.Integer")){
							textcell.setCellValue((Integer)(value));
						}else if (field.getGenericType().toString().equals("class java.lang.Double")) {
							textcell.setCellValue((Double)(value));
						}else if (field.getGenericType().toString().equals("class java.util.Date")){
							textcell.setCellValue(simpleDateFormat.format((Date)value));;
						}else{ // 如果type是类类型，则前面包含"class "，后面跟类名  
					        textcell.setCellValue(value.toString());
					    }
					}else {
						textcell.setCellValue("无数据");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					textcell.setCellValue("无数据");
					e.printStackTrace();
				}
			  j++;
        }
        i++;
      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 自动伸缩列（如非必要，请勿打开此方法，耗内存）
   * @param size 列数
   */
  private static void autoSizeColumn(Integer size) { 
    for (int j = 0; j < size; j++) {
      sheet.autoSizeColumn(j);
    }
  }
}
