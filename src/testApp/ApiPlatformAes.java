package testApp;

import net.sf.json.JSONObject;
public class ApiPlatformAes {
	public static String getReaApi(String cmd){
		String secret = "b5d026e3e0e5cfb0";//测试
		secret = "ed19ef7b9c7e0987";//测试
		String url="http://localhost:8080/api";
		url="http://test69.tuling123.com/openapi/api"; 
		String key="a3e3e1621f6e44268cf53c7675648064";//微信机器人
		return getReqApi( key, secret, url,cmd);
	}
	public static void main(String[] args) {
		System.out.println(getReaApi("今天天气怎么样"));
	}
	public static String getReqApi(String key,String secret,String url,String cmd){
		JSONObject json = new JSONObject();
		json.put("key", key);
		json.put("info",cmd);
		String userId="74340106";
		json.put("userid",userId);
		JSONObject fromObject = JSONObject.fromObject(getReq());
		json.accumulateAll(fromObject);
		String timestamp = System.currentTimeMillis()+"";
		System.out.println("原内容："+json.toString());
		String result=null;
		try {
			result = ConnectUtil.post(url,json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getReq(){
		String userId="1767054";
				userId="1767022";
		userId="74340106";
		String account="turing_os_01@163.com";
		 int operateState=13;
		String appKey="vehicle.crosstalk";
		String appSate="\"appState\":{\"appKey\":\""+appKey+"\",\"operateState\":"+operateState+"},";
		appSate="";
		String addr="\"location\": {\"province\": \"北京\",\"city\": \"北京\",\"street\": \"信息路\",\"nearest_poi_name\": \"上地环岛南\",\"latitude\": \"39.45492\",\"longitude\": \"119.239293\"},";
//		addr=""; 
		String userInfo="\"userInfo\":{\"isSuper\":\"0\",\"userId\":\""+userId+"\",\"account\":\""+account+"\"}";
		userInfo="\"userInfo\":{\"isSuper\":\"0\"}";
		String data="{"+addr+appSate+userInfo+"}";
		return data;
	}
}



