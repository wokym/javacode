package htmltoword;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.rtf.RtfWriter2;

 

public class Html2Word {
 public static boolean writeWordFile() {

  boolean w = false;
  String path = "D:\\downt\\htmltoword\\";
  
  try {
   if (!"".equals(path)) {
    
    // 检查目录是否存在
    File fileDir = new File(path);
    if (fileDir.exists()) {
     
     // 生成临时文件名称
     String fileName = "a.doc";
     String content = "<html>" +
           "<head>你好</head>" +
          "<body>" +
            "<table>" +
             "<tr>" +
              "<td>信息1</td>" +              
              "<td>信息2</td>" +              
              "<td>t3</td>" +              
             "<tr>" +
            "</table>" +
            "</body>" +
            "</html>";
     
     byte b[] = content.getBytes("gbk");
     ByteArrayInputStream bais = new ByteArrayInputStream(b);
     POIFSFileSystem poifs = new POIFSFileSystem();
     DirectoryEntry directory = poifs.getRoot();
     DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
     FileOutputStream ostream = new FileOutputStream(path+ fileName);
     poifs.writeFilesystem(ostream);
     bais.close();
     ostream.close();
     
    }
   }

  } catch (IOException e) {
   e.printStackTrace();
  }

  return w;
 }
 public static boolean writeHtmlWordFile(String htmlPath,String wordPath) {
	  boolean w = false;
	  if(htmlPath==null||wordPath==null) return w;
	  try {
	    
	    // 检查目录是否存在
		File wordFile=new File(wordPath);
		File htmlFile=new File(htmlPath);
	    File fileDir =wordFile.getParentFile();
	    if (!fileDir.exists()?fileDir.mkdirs():true&&htmlFile.exists()) {
	     POIFSFileSystem poifs = new POIFSFileSystem();
	     FileInputStream fileInputStream = new FileInputStream(htmlFile);
	     poifs.getRoot().createDocument("WordDocument", fileInputStream);
	     FileOutputStream ostream = new FileOutputStream(wordFile);
	     poifs.writeFilesystem(ostream);
	     ostream.close();
	     fileInputStream.close();
	    }
	  } catch (IOException e) {
	   e.printStackTrace();
	  }

	  return w;
	 }
 public static void imgHtml() throws Exception{
	 OutputStream out = new FileOutputStream("D:\\downt\\htmltoword\\soso.doc");  
     Document document = new Document(PageSize.A4);  
     RtfWriter2.getInstance(document, out);  
     document.open();  
     Paragraph context = new Paragraph();  
     String s = 
    "上传的图片<img style=\"width: 230px; height: 97px;\" src=\"http://106.2.179.246:11070/questionbank/umeditor/jsp/upload/20160407/62461460001633592.jpg\" />";
     // Image img = Image.getInstance("D:\\图片\\2.jpg");  
     // img.setAbsolutePosition(0, 0);//  
     // document.add(img);  
     StyleSheet ss = new StyleSheet();  
     List htmlList = HTMLWorker.parseToList(new StringReader(s), ss);  
     for (int i = 0; i < htmlList.size(); i++) {  
         com.lowagie.text.Element e = (com.lowagie.text.Element) htmlList  
                 .get(i);  
         context.add(e);  
     }  
     document.add(context);  
     document.close();  
 }
 
 public static void main(String[] args) throws Exception{
//	 imgHtml() ;
	 writeHtmlWordFile("D:\\downt\\htmltoword\\word1.html", "D:\\downt\\htmltoword\\test2TU.doc");
 }
 
}
