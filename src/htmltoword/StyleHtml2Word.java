package htmltoword;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class StyleHtml2Word {

	private StyleHtml2Word() {
	}

	private static String CHARSET = "gbk";

	private static String PATH = "D:\\downt\\htmltoword\\";

	public static String createWordFile(String fileName, String content) {
		OutputStreamWriter os = null;
		FileOutputStream fos = null;
		try {
			if (fileName.indexOf(".doc") > -1) {
				fileName = fileName.substring(0, fileName.length() - 4);
			}

			File file = new File(PATH);

			if (!(file.exists() && file.isDirectory())) {
				file.mkdirs();
			}

			fileName = PATH + "" + fileName + "-" + System.currentTimeMillis()
					+ ".doc";

			File targetFile = new File(fileName);
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			fos = new FileOutputStream(fileName);
			os = new OutputStreamWriter(fos, CHARSET);
			os.append(content.toString());
			os.flush();
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String ss="<html><style type='text/css'>.fff{width: 230px; height: 97px;}</style><div ><img class='fff' src='http://106.2.179.246:11070/questionbank/umeditor/jsp/upload/20160407/62461460001633592.jpg' /><p style=\'position: relative;left: 50%;\'>1.中午</p><p>A中午</p><p>B中午</p><p>C:<span class='mathquill-embedded-latex\' style=\'width: 30px; height: 39px;\'>^2/_3</span></p><p><br/></p></div></html>";
//		String html = "<html><div ><img style=\"width: 230px; height: 97px;\" src=\"http://106.2.179.246:11070/questionbank/umeditor/jsp/upload/20160407/62461460001633592.jpg\" /><p style=\"position: relative;left: 50%;\">1.中午</p><p>A中午</p><p>B中午</p><p>C:<span class=\"mathquill-embedded-latex\" style=\"width: 30px; height: 39px;\">^2/_3</span></p><p><br/></p></div></html>";
		createWordFile("fname",ss);
	}
}