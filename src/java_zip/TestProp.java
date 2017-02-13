package java_zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


public class TestProp {
	public static void main(String[] args) throws Exception {
		/*File fie=new File("d:/downt/build.prop");
	    Properties prop = new Properties();//属性集合对象      
	    FileInputStream fis = new FileInputStream(fie);//属性文件流      
	    prop.load(fis);//将属性文件流装载到Properties对象中    
	    fis.close();
	    System.out.println(prop.getProperty("ro.product.board"));*/
//		String str="^.*[\\.]zip$";
		System.out.println(getUUID());
		String str="asdfasd.zip.txt";
		System.out.println(str.substring(str.indexOf(".")));
		
	}
	//得到uuid主键
		public static String getUUID(){
			 String s = UUID.randomUUID().toString(); 
		        //去掉“-”符号 
		    return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
		}
}
