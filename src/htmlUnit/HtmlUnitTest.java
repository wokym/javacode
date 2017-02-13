package htmlUnit;


import java.io.File;
import java.io.PrintStream;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitTest {
	public static void main(String[] args) throws Exception {
		getHtml();
	}
	
	public static void getHtml() throws Exception{
//		http://m.toutiao.com/#channel=__all__
//		http://3g.163.com/touch/news/subchannel/all?from=index.sitemap&nav=1&version=v_standard
		 String str;
	        //创建一个webclient
	        WebClient webClient = new WebClient(BrowserVersion.CHROME);
	        //htmlunit 对css和javascript的支持不好，所以请关闭之
	        webClient.getOptions().setJavaScriptEnabled(true);
	        webClient.getOptions().setThrowExceptionOnScriptError(false); //
	        webClient.getOptions().setCssEnabled(false);
	        //获取页面
	        HtmlPage page = webClient.getPage("http://3g.163.com/touch/news/subchannel/all?from=index.sitemap&nav=1&version=v_standard");
	        PrintStream printStream = new PrintStream(new File("C:\\kym\\testFile\\print.txt"));
			System.setOut(printStream);
			System.out.println( str = page.asXml());
			printStream.close();
//	        //获取页面的TITLE
//	        str = page.getTitleText();
//	        System.out.println(str);
//	        //获取页面的XML代码
//	       
//	        System.out.println(str);
//	        //获取页面的文本
//	        str = page.asText();
//	        System.out.println(str);
	        //关闭webclient
	        webClient.closeAllWindows();
	}
}
