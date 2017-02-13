package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class CompanyUtils {

	// 字符串转换为字符串数组，如，123:234,mdfjd'dlfj
	public static String[] String2Array(String str) {
		return str.split(",");
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

	// 字符串转换为int数字
	public static int String2Int(String str) {

		if (StringUtils.isNotEmpty(str)) {
			return Integer.parseInt(StringUtils.trim(str));
		} else {
			return 0;
		}
	}
public static void main(String[] args) {
	System.out.println(getUUID());
}
	// 字符串转换为long数字
	public static long String2Long(String str) {

		if (StringUtils.isNotEmpty(str)) {
			return Long.parseLong(StringUtils.trim(str));
		} else {
			return 0;
		}
	}

	// 是否包含true 或者false
	public static boolean judgeT2F(String str) {

		if (StringUtils.isNotEmpty(str)) {
			if (StringUtils.equalsIgnoreCase(str, "false")) {
				return false;
			} else if (StringUtils.equalsIgnoreCase(str, "true")) {
				return true;
			}
			return false;
		} else {
			return false;
		}
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

	// 将string数组转化为integer
	public static Integer[] arrayToInt(String[] sts) {
		if (sts == null)
			return null;
		List<Integer> im = new ArrayList<Integer>();
		for (String str : sts) {
			if (StringUtils.isNotBlank(str))
				im.add(Integer.parseInt(str));
		}
		Integer[] array = (Integer[]) im.toArray(new Integer[0]);
		return array;
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
				throw new Exception("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
			httpConn.disconnect();
		}
		return null;
	}

	// 解析数据库重复信息(数据库格式变化)
	public static String getExceptionMessage(Exception e, int i) {
		String resp = null;
		try {
			String message = e.getCause().toString();
			Pattern p = Pattern.compile("\\([^\\)]+\\)");
			Matcher m = p.matcher(message);
			m.find();
			m.find();
			String group = m.group();
			String[] split = group.split("\\(|\\)");
			resp = split[i].split(",")[0];
		} catch (NullPointerException e0) {
			resp = e.getMessage();
		}
		return resp == null ? null : resp.trim();
	}
}
