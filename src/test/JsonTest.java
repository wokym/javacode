package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class JsonTest {
	public static void main(String[] args) throws Exception {
//		System.out.println(new BB().toString());
//		System.out.println(new Date().getTime());
//		String aaa="aaaa/ssss/sss/ddd/sss";
//		System.out.println(aaa.substring(0, aaa.lastIndexOf("/")+1));
//System.out.println(aaa.substring(aaa.lastIndexOf("/")-1, aaa.length()));
//		InetAddress addr = InetAddress.getLocalHost();
//		String ip=addr.getHostAddress().toString();//获得本机IP
//		String address=addr.getHostName().toString();//获得本机名称
//		System.out.println(ip+"================"+address);
		String ss="%E4%B8%8D%E8%A6%81%E5%88%A0%E6%88%91%E5%95%8A%EF%BC%81";
//		iso-8859-1
		String str3 = java.net.URLDecoder.decode(ss, "UTF-8");//解码
		System.out.println(str3);
		Boolean isUser=true;
		if(isUser==null){//用户更新，校级同步
			System.out.println("null");
		}
		else if(isUser){//同步本级单位
			System.out.println("true");
		}else {//获取父级单位同步
			System.out.println("false");
		}
	}
}
class BB{
	public String aaa="asd";
	public String bbb="bbbb";
} 