import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONObject;


public class JsonToObject {
	public static void main(String[] args) {
		JSONObject result=new JSONObject();
//		String url="啊发<a target=\"_self\" href=\"\"http://www.tuling123.com\">生的发</a>生";
		String url="\">\'\"";
		result.put("text", url);
		result.put("aaa", url+"my</a>");
		System.out.println(result.toString());
		String json = getJson(result);
//		JSONObject fromObject = JSONObject.fromObject(json);
//		System.out.println(fromObject.toString());
//		ObjectMapper om = new ObjectMapper();
//		om.writeValueAsString(result);
//		System.out.println(resul);
	}
	public static String getJson(JSONObject result){
		StringBuffer sb=null;
		try {
			Iterator<String> keys = result.keys();
			sb=new StringBuffer("{");
			while (keys.hasNext()) {
				 String next = keys.next();
				 Object object = result.get(next);
				 String val=result.get(next)==null?"":result.get(next).toString();
//				 val = val.replaceAll("\"", "\\\\\"");
				 sb.append("\""+next+"\":\""+val+"\",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("}");
		} catch (Exception e) {
			return "";
		}
		return sb.toString();
	}
}
