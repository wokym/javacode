package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import baiduyuyin.json.JSONObject;

//import net.sf.json.JSONObject;

public class RegexTest {
	public static void main(String[] args) {
//		String obj="{\"code\": 100000,\"text\": \"<font color=RED><b>问：</b></font>古先生有一晚从公司的办公室走出，去拜访几位顾客。当他回家后，忽然发觉钥匙还留在办公室里，忘了带出来。"
//				+ "但是他仍很快走进家门。他并不是爬墙进去的，也没有把备用钥匙藏在别处。他到底是怎么进去的。<br><font color=RED><b>答：</b></font>住宅和公司在同一栋大楼里<br>\\\r\"}";
		String str="<font color=RED><b>问：<\\/b><\\/font>古先生有一晚从公司的办公室走出，去拜访几位顾客。当他回家后，忽然发觉钥匙还留在办公室里，忘了带出来。但是他仍很快走进家门。"
				+ "他并不是爬墙进去的，也没有把备用钥匙藏在别处。他到底是怎么进去的。<br><font color=RED><b>答：<\\/b><\\/font>住宅和公司在同一栋大楼里<br>\r";
//		JSONObject fromObject = JSONObject.fromObject(obj);
//		System.out.println(fromObject);
		System.out.println(str);
		System.out.println(delHTMLTag(str));
		fenz();
	}
	
	
    public static String delHTMLTag(String htmlStr){ 
    	htmlStr=htmlStr.replace("<br>", "\n");
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        return htmlStr.trim(); //返回文本字符串 
    } 
    public static void fenz(){
    		String srtr="^(\\S{0}蠢货)\\1$";
    		System.out.println("蠢货蠢货".matches(srtr));
    }
}
