package util;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

public class ObjectXLSTemplate<T> {
	public  void generateXLS(OutputStream os,List<T> lists,XLSCallBack<T> xlsCallBack){
		//生成一个xls文件
		HSSFWorkbook work =new HSSFWorkbook();
		//创建一个表
		HSSFSheet sheet=work.createSheet(xlsCallBack.getTitle());
		//合并单元格
		CellRangeAddress cellRangeAddress=new CellRangeAddress(0, 0, 0,(xlsCallBack.getColumnsName().length-1));
		sheet.addMergedRegion(cellRangeAddress);
		//创建一行
	    HSSFRow row=sheet.createRow(0);
	   //设置单元格样式
	   HSSFCellStyle cellStyle = work.createCellStyle();//创建表格样式
	   //加边框
	   cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	   cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	   cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	   cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	   //设置字体居中
	   cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	   //设置字体
	   HSSFFont font =work.createFont();
	   font.setColor(HSSFColor.BLUE.index);
	   font.setFontHeight((short)250);
	   font.setFontName("华文新魏");
	   font.setBoldweight((short)20);
	   cellStyle.setFont(font);
	   //设置背景色
	   cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	   //设置前景色
	   cellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		//创建单元格
	    for(int i=0;i<5;i++){
	       HSSFCell cell= row.createCell(i);
	   	   cell.setCellValue(xlsCallBack.getTitle());
	   	   cell.setCellStyle(cellStyle) ;
	    }
	   //==============head样式===========================
	  //设置单元格样式
	   HSSFCellStyle headCellStyle = work.createCellStyle();//创建表格样式
	   //加边框
	   headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	   headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	   headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	   headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	   
	   //设置字体居中
	   headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	   //设置字体
	   HSSFFont headFont =work.createFont();
	   headFont.setColor(HSSFColor.BLUE.index);
	   headFont.setFontHeight((short)250);
	   headFont.setFontName("华文新魏");
	   headFont.setBoldweight((short)20);
	   headCellStyle.setFont(font);
	   
	   HSSFRow headRow=sheet.createRow(1);
	   for(int i=0;i<xlsCallBack.getColumnsName().length;i++){
		   HSSFCell headCell=headRow.createCell(i);
		   headCell.setCellValue(xlsCallBack.getColumnsName()[i]);
		   headCell.setCellStyle(headCellStyle);
	   }
	   
	   //=============数据================
	   
	 //设置单元格样式
	   HSSFCellStyle valueCellStyle = work.createCellStyle();//创建表格样式
	   //加边框
	   valueCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	   valueCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	   valueCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	   valueCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	   
	   //设置字体居中
	   valueCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	   //设置字体
	   HSSFFont valueFont =work.createFont();
	   valueFont.setColor(HSSFColor.BLACK.index);
	   //valueFont.setFontHeight((short)150);
	   valueFont.setFontName("宋体");
	  // valueFont.setBoldweight((short)15);
	   valueCellStyle.setFont(valueFont);
	   for(int i=0;i<lists.size();i++){
		   HSSFRow valueRow=sheet.createRow(i+2);//前面已经写两行了
		   Object[] values=xlsCallBack.getValues(lists.get(i));
		   for(int j=0;j<values.length;j++){
			   HSSFCell valueCell=valueRow.createCell(j);
			   valueCell.setCellValue(values[j].toString());
			   valueCell.setCellStyle(valueCellStyle);
		   }
		   
	   }
	   //=================指定列宽======================
	   for(int i=0;i<xlsCallBack.getColumnsWidth().length;i++){
		  sheet.setColumnWidth(i, xlsCallBack.getColumnsWidth()[i]);//设置列宽
	   }
	   //=======写文件=================================
	   try {
		work.write(os);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}