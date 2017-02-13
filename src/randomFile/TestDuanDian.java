package randomFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;

import sun.net.www.protocol.http.HttpURLConnection;


public class TestDuanDian {
	  public static void main(String[] args) throws IOException {
			String sturl="http://mirrors.hust.edu.cn/apache/mina/mina/2.0.9/apache-mina-2.0.9-bin.tar.gz";
			//http://mirror.bit.edu.cn/apache/mina/mina/2.0.9/apache-mina-2.0.9-bin.tar.gz
		  URL url = new URL(sturl);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			// 设置下载位置
			File file = new File("d:\\downt\\mina.gz");
			
			long length = file.length();
			int start = (int)length;
			if(length!=0){
				connection.setRequestProperty("Range",
						"bytes=" + length + "-");
			}
			// 设置文件写入位置
			RandomAccessFile raf = new RandomAccessFile(file, "rwd");
			raf.seek(start);
			// 开始下载
			InputStream inputStream=null;
//			if (connection.getResponseCode() == 200)
//			{
				// 读取数据
			System.out.println("kais----");
				inputStream = connection.getInputStream();
				byte buf[] = new byte[1024 *128];
				int len = -1;
				while ((len = inputStream.read(buf)) != -1)
				{
					// 写入文件
					start+=len;
					System.out.println(start+"================"+len);
					raf.write(buf, 0, len);
				}
//			}3541531
				System.out.println("over----");
			inputStream.close();
			raf.close();
			connection.disconnect();
	  }  
}
