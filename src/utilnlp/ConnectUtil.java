package utilnlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectUtil {
	//得到十以内数字大写
	public static String getBigNum(int i){
		String [] num={"零","一","二","三","四","五","六","七","八","九","十"};
		if(i<=10)return num[i];
		else return "";
	}

	// 字符串转换为字符串数组，如，123:234,mdfjd'dlfj
	public static String[] String2Array(String str) {
		return str.split(",");
	}
	private  static  int order_num=0;
	public  static int getOrder(){
		return order_num++;
	}

	// 数字字符串数组转换为整数数组
	public static Integer[] String2IntArray(String str) {
		String[] array = String2Array(str);
		Integer[] temp = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			temp[i] = Integer.parseInt(array[i].trim());
		}
		return temp;
	}



	public static Date getSimpDate(String patten, String date) {
		SimpleDateFormat sf = new SimpleDateFormat(patten);
		Date parse = null;
		try {
			parse = sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parse;
	}


	public static String getArray2String(String[] array, String separator) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = "";
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			// result.append(array[i]);
			if (i > 0) {
				result.append(separator);
			}
			if (array[i] != null) {
				result.append(array[i]);
			}
		}
		return result.toString();
	}

	// 得到uuid主键
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}


	// 发送一个post请求,参数形式key1=value1&key2=value2...
	public static String post(String path, Map map) throws Exception {
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			boolean startsWith = path.startsWith("http");
			if (!startsWith) {
				path = "http://" + path;
			}
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);// 打开写入属性
			httpConn.setDoInput(true);// 打开读取属性
			
			httpConn.setRequestProperty( "Content-Type", "application" );
			httpConn.setRequestProperty( "Accept", "application/json" );
			httpConn.setRequestProperty( "Authorization", "token" );
			httpConn.connect();
			
			OutputStream outputStream = httpConn.getOutputStream();
			OutputStreamWriter outputStreamReader = new OutputStreamWriter(
					outputStream, "utf-8");
			// 发送post请求参数
			out = new PrintWriter(outputStreamReader);
			StringBuilder sb = new StringBuilder();
			if (map != null) {
				Set<Map.Entry> entrySet = map.entrySet();
				for (Map.Entry entry : entrySet) {
					sb.append(entry.getKey() + "=" + entry.getValue());
					sb.append("&");
				}
				String substring = sb.substring(0, sb.length() - 1);
				out.println(substring);
			}
			out.flush();
			// 读取响应
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				in = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "utf-8"));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}
				return content.toString();
			} else {
				System.out.println("请求异常code==="+httpConn.getResponseCode() );
				throw new Exception("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				httpConn.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	// 发送一个post请求
	public static String get(String path) throws Exception {
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		try {
			boolean startsWith = path.startsWith("http");
			if (!startsWith) {
				path = "http://" + path;
			}
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(100000);
			httpConn.setReadTimeout(100000);
			httpConn.setRequestMethod("GET");
			httpConn.setDoInput(true);// 打开读取属性
			// 读取响应
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				in = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "utf-8"));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}
				return content.toString();
			} else {
				System.out.println("请求异常code==="+httpConn.getResponseCode() );
				throw new Exception("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				httpConn.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	
	// 发送一个post请求
	public static String post(String path, String data) throws Exception {
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			boolean startsWith = path.startsWith("http");
			if (!startsWith) {
				path = "http://" + path;
			}
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(100000);
			httpConn.setReadTimeout(100000);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);// 打开写入属性
			httpConn.setDoInput(true);// 打开读取属性
			
			httpConn.setRequestProperty( "Content-Type", "application" );
			httpConn.setRequestProperty( "Accept", "application/json" );
			httpConn.setRequestProperty( "Authorization", "token" );
			httpConn.connect();
			OutputStream outputStream = httpConn.getOutputStream();
			OutputStreamWriter outputStreamReader = new OutputStreamWriter(
					outputStream, "utf-8");
			// 发送post请求参数
			out = new PrintWriter(outputStreamReader);
			out.write(data);
			out.flush();
			out.close();
			// 读取响应
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				in = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "utf-8"));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}
				return content.toString();
			} else {
				System.out.println("请求异常code==="+httpConn.getResponseCode() );
				throw new Exception("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				httpConn.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

}
