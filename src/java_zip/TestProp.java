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
	    Properties prop = new Properties();//���Լ��϶���      
	    FileInputStream fis = new FileInputStream(fie);//�����ļ���      
	    prop.load(fis);//�������ļ���װ�ص�Properties������    
	    fis.close();
	    System.out.println(prop.getProperty("ro.product.board"));*/
//		String str="^.*[\\.]zip$";
		System.out.println(getUUID());
		String str="asdfasd.zip.txt";
		System.out.println(str.substring(str.indexOf(".")));
		
	}
	//�õ�uuid����
		public static String getUUID(){
			 String s = UUID.randomUUID().toString(); 
		        //ȥ����-������ 
		    return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
		}
}
