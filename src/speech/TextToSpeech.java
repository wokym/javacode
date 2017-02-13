package speech;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @className: Speech 文字转音频  
 * @description: TODO 
 * @since  2015-5-20	下午03:33:16
 * @author 杨伟中 yangweizhong_uzoo_cn
 */
public class TextToSpeech {
	private static String token = "";

	/*
	 * 主调函数
	 */
	public static byte[] getTextToSpeechResult(String text) {
		/*
		 * 先获取token
		 */
		try {
			token = RestApiToken.getToken();
			return textToSpeech(text);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 音频文件的获取byte[]
	 */
	private static byte[] textToSpeech(String text) throws IOException {
		// 拼接请求百度文字转语音接口的url
		String url = "http://tsn.baidu.com/text2audio?tex=" + text
				+ "&lan=zh&cuid=001&ctp=1&per=1&tok=" + token;
		HttpURLConnection connection = null;
		InputStream is = null;
		byte[] result = new byte[0];
		/** 发送get请求 */
		try {
			URL getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl
					.openConnection();
			connection.connect();
			is = connection.getInputStream();
			byte[] temp = new byte[is.available()];
			int size = 0;
			while ((size = is.read(temp)) != -1) {
				byte[] readBytes = new byte[size];
				System.arraycopy(temp, 0, readBytes, 0, size);
				result = mergeArray(result, readBytes);
			}
			is.close();
			connection.disconnect();
		} catch (Exception e) {
			if(null != is){
				is.close();
			}
			if(null != connection){
				connection.disconnect();
			}
			e.printStackTrace();
			//如果请求异常，再请求一次
			try{
				URL getUrl = new URL(url);
				connection = (HttpURLConnection) getUrl
						.openConnection();
				connection.connect();
				is = connection.getInputStream();
				byte[] temp = new byte[is.available()];
				int size = 0;
				while ((size = is.read(temp)) != -1) {
					byte[] readBytes = new byte[size];
					System.arraycopy(temp, 0, readBytes, 0, size);
					result = mergeArray(result, readBytes);
				}
				is.close();
				connection.disconnect();
			}catch(Exception e1){
				e1.printStackTrace();
				if(null != is){
					is.close();
				}
				if(null != connection){
					connection.disconnect();
				}
			}
		}
		return result;
	}

	/***
	 * 合并字节数组
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] mergeArray(byte[]... a) {
		// 合并完之后数组的总长度
		int index = 0;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i].length;
		}
		byte[] result = new byte[sum];
		for (int i = 0; i < a.length; i++) {
			int lengthOne = a[i].length;
			if (lengthOne == 0) {
				continue;
			}
			// 拷贝数组
			System.arraycopy(a[i], 0, result, index, lengthOne);
			index = index + lengthOne;
		}
		return result;
	}
}
