package com.yb.excel.test.one;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
  
public class ExportExcelUtils {  
  
    /** 
     * @Title: exportExcel 
     * @Description: 导出Excel的方法 
     * @author: evan @ 2014-01-09  
     * @param workbook  
     * @param sheetNum (sheet的位置，0表示第一个表格中的第一个sheet) 
     * @param sheetTitle  （sheet的名称） 
     * @param headers    （表格的标题） 
     * @param result   （表格的数据） 
     * @param out  （输出流） 
     * @throws Exception 
     */  
    @SuppressWarnings("deprecation")
	public void exportExcel(HSSFWorkbook workbook, int sheetNum,  
            String sheetTitle, String[] headers, List<List<Object>> result) throws Exception {  
        // 生成一个表格  
    
        HSSFSheet sheet = workbook.createSheet();  
        workbook.setSheetName(sheetNum, sheetTitle);  
        // 设置表格默认列宽度为20个字节  
        sheet.setDefaultColumnWidth((short) 20);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setFontHeightInPoints((short) 12);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
  
        // 指定当单元格内容显示不下时自动换行  
        style.setWrapText(true);  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (int i = 0; i < headers.length; i++) {  
            HSSFCell cell = row.createCell((short) i);  
          
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text.toString());  
        }  
        // 遍历集合数据，产生数据行  
        if (result != null) {  
            int index = 1;  
            for (List<Object> m : result) {  
                row = sheet.createRow(index);  
                int cellIndex = 0;  
                for (Object str : m) {  
                    HSSFCell cell = row.createCell((short) cellIndex);
                    try {
                    	if(str instanceof Integer){
                    		cell.setCellValue(Integer.valueOf(str.toString()));
                    	}else if (str instanceof Double) {
                    		cell.setCellValue(Double.valueOf(str.toString()));
						}else {
							cell.setCellValue(str.toString());
						}
                    	
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						cell.setCellValue("无数据");
					}
                    cellIndex++;  
                }  
                index++;
            }  
        }  
    }  
}  