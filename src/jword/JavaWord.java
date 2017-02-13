package jword;

	import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

	import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import org.textmining.text.extraction.WordExtractor;

import sun.org.mozilla.javascript.internal.ast.WithStatement;
import junit.framework.TestCase;
	/**
	 * 读写doc
	 * @author wangzonghao
	 *
	 */
	public class JavaWord {
	 /**
	  * 读入doc
	  * @param doc
	  * @return
	  * @throws Exception
	  */
	 public static String readDoc(String doc) throws Exception {
	     // 创建输入流读取DOC文件
	      FileInputStream in = new FileInputStream(new File(doc));
	      WordExtractor extractor = null;
	      String text = null;
	     // 创建WordExtractor
	      extractor = new WordExtractor();
	     // 对DOC文件进行提取
	      text = extractor.extractText(in);
	     return text;
	 }
	 /**
	  * 写出doc
	  * @param path
	  * @param content
	  * @return
	  */
	 public static boolean writeDoc(String path, String content) { 
	     boolean w = false; 
	    try { 

	    // byte b[] = content.getBytes("ISO-8859-1"); 
	    byte b[] = content.getBytes(); 

	    ByteArrayInputStream bais = new ByteArrayInputStream(b); 

	    POIFSFileSystem fs = new POIFSFileSystem(); 
	    DirectoryEntry directory = fs.getRoot(); 

	    DocumentEntry de = directory.createDocument("WordDocument", bais); 
	    
	    FileOutputStream ostream = new FileOutputStream(path); 

	    fs.writeFilesystem(ostream); 

	    bais.close(); 
	    ostream.close(); 

	    } catch (IOException e) { 
	    e.printStackTrace(); 
	    } 
	    return w; 
	    } 
	 public static void main(String[] args) {
		 POIUtilTest poi=new POIUtilTest();
//		 String readeText = poi.readeText(new File("D:/work_space/identification/src/main/java/cct/services/base/common/datasource/BaseDao.java"));
//		 System.out.println(readeText);
		 List<File> files=new ArrayList<File>();
		 poi.findFile(new File("D:/work_space/identification/src/main/java/cct/"), ".java",files );
		 StringBuilder sb=new StringBuilder();
		 for(File f:files){
			 sb.append(poi.readeText(f));
			 sb.append("\r\n");
		 }
		 poi.testWriteDoc("D:/work_space/demo.doc",sb.toString());
	 }
}


class POIUtilTest extends TestCase {
		 public void testReadDoc(String wordAddr) {
			 try{
		            String text = JavaWord.readDoc("D:/work_space/demo.doc");
		            System.out.println(text);
		         }catch(Exception e){
		             e.printStackTrace();
		      }
		 }
		 
		 public void testWriteDoc(String wordAddr,File url,String format) {
			  try {
//					  	boolean b = JavaWord.writeDoc(wordAddr,url);
				  
				  } catch (Exception e) {
					  	e.printStackTrace();
			  }
		 }
		 public void testWriteDoc(String wordAddr,String str) {
			  try {
					  	boolean b = JavaWord.writeDoc(wordAddr,str); 
				  } catch (Exception e) {
					  	e.printStackTrace();
			  }
		 }
		 
		 public String readeText(File file){
			 ByteArrayOutputStream out=null;
			 String str=null;
			 try {
				 out=new ByteArrayOutputStream();
				 InputStream inp=new FileInputStream(file);
				 byte [] bytes=new byte[1024*100]; 
				 int read =-1;
				while((read=inp.read(bytes))!=-1){
					out.write(bytes, 0, read);
				}
				str=new String(out.toByteArray(),"utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return str;
		 }
		 //
		 public void findFile(File url,String format,List<File> fs){
			 if(url.isDirectory()){
				 File[] listFiles = url.listFiles();
				 for(File file:listFiles){
					 if(file.isDirectory())
						 findFile(file,format,fs);
					 else
						 if(file.getName().endsWith(format))
							 fs.add(file);
				 }
			 }else{
				 if(url.getName().endsWith(format))
					 fs.add(url);
			 }
		 }
	}
