package urlDownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import sun.misc.BASE64Encoder;

public class FileDownload {
	private static String localFileUrl="D:\\downt\\hadoopfile\\hly-temp-normal.txt";
	private static String localtion="http://www1.ncdc.noaa.gov/pub/data/normals/1981-2010/products/hourly/hly-temp-normal.txt";
	
	public static void main(String[] args) throws Exception {
		InputStream xml = getXml(localtion);
		PrintWriter pw=new PrintWriter(new File(localFileUrl));
	    String tempStr="";
	    BufferedReader  post=new BufferedReader(new InputStreamReader(xml,"utf-8"));
	    while((tempStr=post.readLine())!=null){
	    	pw.append(tempStr);
	    }
	    pw.flush();
	    post.close();xml.close();
}
	//����һ��post����,������ʽkey1=value1&key2=value2...
	 public static void get(String path) throws Exception{
		  HttpURLConnection httpConn=null;
		PrintWriter pw=new PrintWriter(new File(localFileUrl));
		BufferedReader post = null;
		  try {
		   boolean startsWith = path.startsWith("http");
		   if(!startsWith){
			   path="http://"+path;
		   }
		   URL url=new URL(path);
		   httpConn=(HttpURLConnection)url.openConnection();
		   httpConn.setConnectTimeout(30000);  
		   httpConn.setReadTimeout(30000); 
		   httpConn.setRequestMethod("GET");
//		   httpConn.setDoOutput(true);// ��д������
		   httpConn.setDoInput(true);// �򿪶�ȡ����
		   //��ȡ��Ӧ
		   if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){
		    String tempStr="";
		    post=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"utf-8"));
		    while((tempStr=post.readLine())!=null){
		    	pw.append(tempStr);
		    }
		    pw.flush();
		   }else{
		    throw new Exception("�������������!");
		   }
		  } catch (IOException e) {
		   e.printStackTrace();
		  }finally{
			  post.close();
			  pw.close();
		   httpConn.disconnect();
		  }
	 }
	 public static InputStream getXml(String str) throws Exception{
			URLConnection connection = null; 
			InputStream in=null;
			try {
				connection=getURLConnection(str);
				in=(InputStream) connection.getInputStream();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}//��ȡ������
			return in;
		}
		public static HttpURLConnection getURLConnection(String str) throws Exception{
			URL url=null;
			try {
				url = new URL(str);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
			URLConnection connection=null;
			try {
				connection = url.openConnection();
				connection.setReadTimeout(20000);  
				connection.setConnectTimeout(20000);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HttpURLConnection httpconnection=(HttpURLConnection)connection;
			/**
			 * public static void HttpURLConnection.setFollowRedirects(boolean followRedirects)
				public void HttpURLConnection.setInstanceFollowRedirects(boolean followRedirects)
				ǰ���������е�http�����Ƿ��Զ������ض���
				�������ñ��������Ƿ��Զ������ض���
				���ó�true��ϵͳ�Զ������ض������ó�false������Ҫ�Լ���http reply�з����µ�url
				�Լ��������ӡ�
			 * */
			
			HttpURLConnection.setFollowRedirects(true);
			httpconnection.setRequestProperty("Content-type", "application/x-java-serialized-object"); 
			return httpconnection;
		}
}
