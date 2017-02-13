package cmdpeocess;

import java.util.ArrayList;
import java.util.List;

public class ApkKeyAppend {
	public static boolean apkAppendKey(String cmdUrl,String apkFile,String dataFile,String keyFile){
		List<String> commend=new ArrayList<>();
		commend.add(cmdUrl);
		commend.add(apkFile);
		commend.add(dataFile);
		commend.add(keyFile);
		try { 
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		String path = ApkKeyAppend.class.getClassLoader().getResource("apkfile").getPath();
		String cmdUrl=path+"/run.sh";
		String apkFile=path+"/tf_bak.apk";
		String dataFile=path+"/target";
		String keyFile=path+"/key";
		apkAppendKey(cmdUrl,apkFile,dataFile,keyFile);
	}
}
