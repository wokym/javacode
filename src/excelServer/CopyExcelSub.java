
package excelServer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import util.ObjectXLSTemplate;
import util.XLSCallBack;
import excelDao.DateJob;
import excelDao.Peo;

public class CopyExcelSub {
	public static void main(String[] args) throws Exception {
		CopyExcelSub excelSub=new CopyExcelSub();
		List<DateJob> list=new ArrayList<DateJob>();
		SimpleDateFormat sp=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat da=new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");
		excelSub.readXls("D:\\downt\\eccel\\ces.xls",list ,sp.parse("2016/03/01"),sp.parse("2016/05/01"));	
		List<Peo> allpeo=new ArrayList<Peo>();
		for(DateJob db:list){
			allpeo.addAll(db.getLsitpeo());
		}
		 Collections.sort(allpeo, new Comparator<Peo>(){

			@Override
			public int compare(Peo o1, Peo o2) {
				int flag=o1.getName_code().compareTo(o2.getName_code());
				if(flag==0)
					return o1.getStartDate().compareTo(o2.getStartDate());
				return flag;
			}
			 
		 });
		FileOutputStream file=new FileOutputStream("d:\\7777.xls");
		ObjectXLSTemplate<Peo> objectXLSTemplate =new ObjectXLSTemplate<Peo>();
		objectXLSTemplate.generateXLS(file, allpeo, new XLSCallBack<Peo>(){

			@Override
			public String getTitle() {
				return "四月份打卡记录";
			}

			@Override
			public String[] getColumnsName() {
				String [] stss={"打卡日期","星期","用户","开始打卡时间","最后打卡时间","迟到时间","早退时间","加班打卡"};
				return stss;
			}

			@Override
			public int[] getColumnsWidth() {
				int [] ii={5000,3000,1500,8000,8000,8000,8000,8000};
				return ii;
			}
			@Override
			public Object[] getValues(Peo t) {
				Object[] obj={new SimpleDateFormat("yyyy-MM-dd").format(t.getStartDate()),getXin(t.getStartDate()),t.getName_code(),
						new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(t.getStartDate()),
						new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(t.getEndDate()),getstart(t.getStartDate()),getend(t.getEndDate()),getOverTime(t.getEndDate())};
				return obj;
			}
			//迟到多少秒
			public  String getstart(Date start){
				String time=" 9-15-00";
				SimpleDateFormat datasim=new SimpleDateFormat("yyy-MM-dd");
				SimpleDateFormat fuz=new SimpleDateFormat("yyy-MM-dd HH-mm-ss");
				String format = datasim.format(start);
				Date parse=null;//规定早到时间
				try {
					parse = fuz.parse(format+time);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long longs=start.getTime()-parse.getTime();
				if(longs>1000*60)//迟到
				{
					return "迟到时间："+longs/1000/60/60+"小时："+longs/1000/60%60+"分";
				}else{
					return "";
				}
			}
			
			public String getXin(Date start){
				Calendar cal = Calendar.getInstance();
				  cal.setTime(start);
				  int a=cal.get(Calendar.DAY_OF_WEEK)-1;
				  String []sts=new String[]{"一","二","三","四","五","六"};
				  if(a!=0)
					  return "星期"+sts[a-1];
				  else
					  return "星期天";
			}
			//加班八点后
			public String getOverTime(Date end){
				String time=" 21-00-00";
				SimpleDateFormat datasim=new SimpleDateFormat("yyy-MM-dd");
				SimpleDateFormat fuz=new SimpleDateFormat("yyy-MM-dd HH-mm-ss");
				String format = datasim.format(end);
				Date parse=null;//规定加班时间
				try {
					parse = fuz.parse(format+time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long longs=parse.getTime()-end.getTime();
				if(longs>0)//以
				{
					return "";
				}else{
					return "加班："+fuz.format(end);
				}
			}
			
			//早退多少秒
			public  String getend(Date end){
				String time=" 18-00-00";
				SimpleDateFormat datasim=new SimpleDateFormat("yyy-MM-dd");
				SimpleDateFormat fuz=new SimpleDateFormat("yyy-MM-dd HH-mm-ss");
				String format = datasim.format(end);
				Date parse=null;//规定下班时间
				try {
					parse = fuz.parse(format+time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long longs=parse.getTime()-end.getTime();
				if(longs>1000*60)//迟到
				{
					return "早退时间："+longs/1000/60/60+"小时："+longs/1000/60%60+"分";
				}else{
					return "";
				}
			}
		} );
		file.close();
	}
	 public void readXls(String url,List<DateJob> list,Date star1,Date endt) throws IOException, Exception{  
		    InputStream is = new FileInputStream( url);  
		    HSSFWorkbook hssfWorkbook = new HSSFWorkbook( is);   
		    // 循环工作表Sheet  
		      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( 0);  
		      // 循环行Row   
		      for(int rowNum = 1; rowNum < hssfSheet.getLastRowNum(); rowNum++){  
		        HSSFRow hssfRow = hssfSheet.getRow( rowNum);  
		        if(hssfRow == null){  
		          continue;  
		        }  
		        //日期
		        HSSFCell date = hssfRow.getCell(1);
		        Date dates = null; 
		        try {
		        	dates = org.apache.poi.ss.usermodel.DateUtil.getJavaDate( date.getNumericCellValue()); 
				} catch (Exception e) {
					throw new RuntimeException();
				}
		        
//		        if(dates.compareTo(star)<0||dates.compareTo(endt)>0) continue;
		        SimpleDateFormat sp=new SimpleDateFormat("yyyy/MM/dd");
		        Date parse = sp.parse(sp.format(dates));
		        DateJob datejob=new DateJob();
		        datejob.setNewDate(parse);
		        
		        if(!list.contains(datejob))//不存在该天记录，加入
		        	list.add(datejob);
		        else{//存在拿出来
		        	for(DateJob dj:list){
		        		if(datejob.equals(dj))
		        			datejob=dj;
		        	}
		        }
		        List<Peo> lsitpeo = datejob.getLsitpeo();//该天的人员信息
		        //处理姓名
		        HSSFCell name_code = hssfRow.getCell(0); 
		        Peo peo=new Peo();
		        if(name_code==null||name_code.toString().trim()=="") continue;
		        String trim = name_code.toString().trim();
		        if(trim.indexOf(".")!=-1)//存在小数点
		        	trim=trim.split("\\.")[0];
		        peo.setName_code(trim);
		        if(lsitpeo.contains(peo)){//存在该人员
		        	for(Peo dj:lsitpeo){
		        		if(peo.equals(dj))
		        			peo=dj;
		        	}
		        }else{//不存在该人员
		        	lsitpeo.add(peo);
		        }
		        //打卡时间
		        HSSFCell rumdate = hssfRow.getCell( 1);
		        Date nowdate = org.apache.poi.ss.usermodel.DateUtil.getJavaDate( rumdate.getNumericCellValue()); 
		        if(peo.getStartDate()==null||nowdate.compareTo(peo.getStartDate())<0) peo.setStartDate(nowdate);  //小于开始时间
		        if(peo.getEndDate()==null||nowdate.compareTo(peo.getEndDate())>0) peo.setEndDate(nowdate);//大于最后时间  
		      }  
		    is.close();
		  }  
		    
		  @SuppressWarnings("static-access")  
		  private String getValue(HSSFCell hssfCell){  
		    if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
		      return String.valueOf( hssfCell.getBooleanCellValue());  
		    }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
		      return String.valueOf( hssfCell.getNumericCellValue());  
		    }else{  
		      return String.valueOf( hssfCell.getStringCellValue());  
		    }  
		  }  
}
