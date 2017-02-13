package testApp;

import java.util.ArrayList;
import java.util.List;

import manageEXCEL.ExcelUtil;

public class TestVehicle {
	public static void main(String[] args) throws Exception {
		String url="C:\\kym\\testFile\\data03.xlsx";
		int sheet=1;
		int startLine=1;
		//0开始
		for(;sheet<10;sheet++){
			List<String[]> info = ExcelUtil.getInfo(url,sheet,startLine,0,0);
			List<String> strs=new ArrayList<>();
			for(String [] stss:info){
				for(String str:stss){
					Thread.sleep(5);
					strs.add(ApiPlatformAes.getReaApi(str));
				}
			}
			ExcelUtil.toWrite(strs, url, sheet, startLine,1);
		}
	}
}
