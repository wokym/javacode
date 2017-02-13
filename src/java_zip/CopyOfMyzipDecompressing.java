package java_zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/** 
 * 程序实现了ZIP压缩。共分为2部分 ： 
 * 压缩（compression）与解压（decompression） 
 * <p> 
 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。 
 * 需在代码中自定义源输入路径和目标输出路径。 
 * <p> 
 * 在本段代码中，实现的是解压部分；压缩部分见本包中compression部分。 
 * @author HAN 
 * 
 */  
public class CopyOfMyzipDecompressing {
	 public static void main(String[] args) {  
	        // TODO Auto-generated method stub  
	        long startTime=System.currentTimeMillis();  
	        try {  
	            ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
	                    "C:\\Users\\Administrator.PC-20150420HXQF\\Desktop\\downt\\system.zip"));//输入源zip路径  
	            BufferedInputStream Bin=new BufferedInputStream(Zin);  
	            String Parent="C:\\Users\\Administrator.PC-20150420HXQF\\Desktop\\downt\\"; //输出路径（文件夹目录）  
	            File Fout=null;  
	            ZipEntry entry;  
	            try {  
	                while((entry = Zin.getNextEntry())!=null){ 
	                    if(!entry.isDirectory())
	                    {
	                    	Fout=new File(Parent,entry.getName());  
//	                    	boolean exists = Fout.getParentFile().exists();
	                    	if(!Fout.getParentFile().exists()){  
	                    		Fout.getParentFile().mkdirs();  
	                    	}  
	                    	FileOutputStream out=new FileOutputStream(Fout);  
	                    	BufferedOutputStream Bout=new BufferedOutputStream(out);  
	                    	int b;  
	                    	while((b=Bin.read())!=-1){  
	                    		Bout.write(b);  
	                    	}  
	                    	Bout.flush();
	                    	Bout.close();  
	                    	out.close();  
	                    }
	                }  
	                Bin.close();  
	                Zin.close();  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        long endTime=System.currentTimeMillis();  
	        System.out.println("耗费时间： "+(endTime-startTime)+" ms");  
	    }  
	 
	 public static void main11(String[] args) {  
	        // TODO Auto-generated method stub  
	        long startTime=System.currentTimeMillis();  
	        try {  
	            ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
	                    "D:\\downt\\evk_6sl_eink-1.8.13.zip"));//输入源zip路径  
	            BufferedInputStream Bin=new BufferedInputStream(Zin);  
	            String Parent="C:\\Users\\Administrator.PC-20150420HXQF\\Desktop\\downt"; //输出路径（文件夹目录）  
	            File Fout=null;  
	            ZipEntry entry;  
	            Properties prop=null;
	            try {  
	            	while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){ 
	   				 if(entry.getName().equals("system/build.prop")){
	   					 prop= new Properties();//属性集合对象  
	   					    prop.load(Zin);//将属性文件流装载到Properties对象中 
	   					    Zin.close();
	   					    break;
	   				 }
	   			 }
	            	System.out.println(prop.getProperty("ro.product.board"));
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        long endTime=System.currentTimeMillis();  
	        System.out.println("耗费时间： "+(endTime-startTime)+" ms");  
	    }  
	 
	 public static void main111(String[] args) {  
		 while(getA()&&getB()){
			 
		 }
	 }

	private static boolean getB() {
		System.out.println("BBBBB+++++++++");
		return false;
	}

	private static boolean getA() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("AAAAA+++++++++");
		
		return true;
	}  
}
