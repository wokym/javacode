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
 * ����ʵ����ZIPѹ��������Ϊ2���� �� 
 * ѹ����compression�����ѹ��decompression�� 
 * <p> 
 * ���¹��ܰ������˶�̬���ݹ��JAVA���ļ��������ԶԵ����ļ������⼶���ļ��н���ѹ���ͽ�ѹ�� 
 * ���ڴ������Զ���Դ����·����Ŀ�����·���� 
 * <p> 
 * �ڱ��δ����У�ʵ�ֵ��ǽ�ѹ���֣�ѹ�����ּ�������compression���֡� 
 * @author HAN 
 * 
 */  
public class CopyOfMyzipDecompressing {
	 public static void main(String[] args) {  
	        // TODO Auto-generated method stub  
	        long startTime=System.currentTimeMillis();  
	        try {  
	            ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
	                    "C:\\Users\\Administrator.PC-20150420HXQF\\Desktop\\downt\\system.zip"));//����Դzip·��  
	            BufferedInputStream Bin=new BufferedInputStream(Zin);  
	            String Parent="C:\\Users\\Administrator.PC-20150420HXQF\\Desktop\\downt\\"; //���·�����ļ���Ŀ¼��  
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
	        System.out.println("�ķ�ʱ�䣺 "+(endTime-startTime)+" ms");  
	    }  
	 
	 public static void main11(String[] args) {  
	        // TODO Auto-generated method stub  
	        long startTime=System.currentTimeMillis();  
	        try {  
	            ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
	                    "D:\\downt\\evk_6sl_eink-1.8.13.zip"));//����Դzip·��  
	            BufferedInputStream Bin=new BufferedInputStream(Zin);  
	            String Parent="C:\\Users\\Administrator.PC-20150420HXQF\\Desktop\\downt"; //���·�����ļ���Ŀ¼��  
	            File Fout=null;  
	            ZipEntry entry;  
	            Properties prop=null;
	            try {  
	            	while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){ 
	   				 if(entry.getName().equals("system/build.prop")){
	   					 prop= new Properties();//���Լ��϶���  
	   					    prop.load(Zin);//�������ļ���װ�ص�Properties������ 
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
	        System.out.println("�ķ�ʱ�䣺 "+(endTime-startTime)+" ms");  
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
