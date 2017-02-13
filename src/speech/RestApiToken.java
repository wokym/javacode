package speech;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

/**
 * 获取百度restapitoken
 * 
 * @author pamchen-1
 * 
 */
public class RestApiToken {
	private static final String apiKey = "Ql9y3ZPCseTfGkyXo9KWhuGo";
	private static final String secretKey = "5cc0d40c836fb78f61967b6583e8f01a";

	/**
	 * 请求百度权限api，获取token
	 * @return
	 */
	public static String getToken() {
		try {
		String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials"
				+ "&client_id=" + apiKey + "&client_secret=" + secretKey;
		HttpURLConnection conn;
			conn = (HttpURLConnection) new URL(getTokenURL)
					.openConnection();
		
			return JSONObject.fromObject(printResponse(conn)).getString("access_token");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 用于获取token
	 */
	private static String printResponse(HttpURLConnection conn) {
		try {
			if (conn.getResponseCode() != 200) {
				return "";
			}
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
