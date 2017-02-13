package manageEXCEL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	public static void main(String[] args) {
		String url="C:\\kym\\testFile\\data02.xlsx";
		int sheet=1;
		int startLine=1;
		//0开始
		List<String[]> info = ExcelUtil.getInfo(url,sheet,startLine,0,0);
		List<String> strs=new ArrayList<>();
		for(String [] stss:info){
			for(String str:stss){
				strs.add(str);
			}
		}
		ExcelUtil.toWrite(strs, url, sheet, startLine,1);
	}
	public  static List<String[]> getInfo(String url,int sheet,int startLine,int startCloum,int endCloum){
		int objs=endCloum-startCloum;
		if(objs<0)return null;
		FileInputStream is=null;
		 Workbook workBook=null;  
		 List<String []> liststr=null;
		try {
			is = new FileInputStream(new File(url));
			 workBook = WorkbookFactory.create(is);
			 Sheet sheetAt = workBook.getSheetAt(sheet);
		   liststr=new ArrayList<>();
		     for(int rowNum = startLine; rowNum <(sheetAt.getLastRowNum()+1); rowNum++){  
		    	 Row row = sheetAt.getRow(rowNum);
		        if(row == null){  
		          continue;  
		        }  
		        String [] sts=new String[objs+1];
		        for(int i=0;i<sts.length;i++){
		        	 Cell cell = row.getCell(startCloum+i);
		        	 String string=cell!=null?cell.toString():null;
		        	if(string!=null&&string.trim()!="")
		        		sts[i]=string;
		        }
		        liststr.add(sts);
		      }  
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return liststr;
	}
	/**
	 * 添加到指定excel文件中。指定列的strs集合内容
	 * 
	 * */
	public static void toWrite(List<String> strs,String url,int sheet,int startline,int cloum){
		try{
			FileInputStream inputOut=new FileInputStream(new File(url));
			 Workbook create = WorkbookFactory.create(inputOut);
			 Sheet sheetAt = create.getSheetAt(sheet);
			 for(int i=0;i<strs.size();i++){
				 Row row = sheetAt.getRow(startline+i);
				 row.createCell(cloum).setCellValue(strs.get(i));
			 }
			 inputOut.close();
			 FileOutputStream fileOut=new FileOutputStream(new File(url));
			 create.write(fileOut);
			 fileOut.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}
}
