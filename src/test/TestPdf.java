package test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

//import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
//import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;
public class TestPdf {
		public void createPDF(String officePath,String pdfPath) throws Exception {
//	        ReleaseManager rm = null;
//	        IDispatch app = null;
//	        try {
//	                rm=new ReleaseManager();
//	                app = new IDispatch(rm, "PDFMakerAPI.PDFMakerApp");
//	                app.method("CreatePDF",new Object[]{officePath,pdfPath});
//	        } catch (Exception e) {
//	                throw e;
//	        } finally {
//	                try {
//	                        app=null;
//	                        rm.release();
//	                        rm = null;
//	                } catch (Exception e) {
//	                        throw e;
//	                }
//	        }              
	}
	public static <T> List<T> []  findlist(List<T> ls,int size){
		double a=ls.size()*1.0/size;
		int floor =(int) Math.ceil(a);
		List<T> [] lis=new List[floor];
		for(int i=0;i<floor;i++){
			List<T> list=new ArrayList<>();
			for(int k=0;k<size&&ls.size()!=0;k++){
				   Random r = new Random();
				    //如果是List的话，用下面的
				   T t = ls.get(r.nextInt(ls.size()));
				   list.add(t);
				   ls.remove(t);
			}
			lis[i]=list;
		}
		return lis;
	}
	public static void main(String[] args) throws Exception {
//			TestPdf one=new TestPdf();
//	        one.createPDF("C:\\zms\\temp\\a.ppt","C:\\zms\\temp\\c.pdf");
//		Date getSysDate = GetSysDate("yyyy-MM-dd","2015-1-1",1,1,0);
//		System.out.println(getSysDate);
		List<String> saa=new ArrayList<>();
		saa.add("aa");
		saa.add("bb");
		saa.add("cc");
		saa.add("dd");
		List<String>[] findlist = findlist(saa,1);
	
		
		double aa=5.2;
		int bb=6;
		int cc=7;
		
		System.out.println((int)Math.ceil(aa));
	} 
	public static Date GetSysDate(String format,String StrDate,int year,int month,int day){  
		Calendar cal=Calendar.getInstance();  
		SimpleDateFormat sFmt=new SimpleDateFormat(format);  
		cal.setTime(sFmt.parse((StrDate),new ParsePosition(0)));  
		cal.add(cal.DATE,day);  
		cal.add(cal.MONTH,month);  
		cal.add(cal.YEAR,year);  
		return cal.getTime();  
	}   
}
