package utilnlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import manageEXCEL.ExcelUtil;

public class TuringSegmentation {
	private static Map<String,Integer> varNum=new HashMap<>();
	private static Map<String,Integer> typeNum=new HashMap<>();
	private static Map<String,Integer> strNum=new HashMap<>();
	static String [] strss={
		"你好啊",
		"你好蠢",
		"你好机器人",
		"你好二哈",
		"你好呀",
		"你好吗",
		"你好笨",
	};
	
	public static void main(String[] args) throws Exception {
//		List<String[]> info = ExcelUtil.getInfo("C:\\kym\\testFile\\data1.xlsx",0,1,7,7);
//		List<String> strs=new ArrayList<>();
//		for(String [] stss:info){
			for(String str:strss){
//				strs.add(str);
				toSeg(str);
			}
//		}
		toprint(varNum);
		toprint(typeNum);
		toprint(strNum);
	}
	
	public static void toSeg(String str) throws Exception{
		String url="http://182.92.152.237:8080/base-segment/getOriginalSegmentation?req=";
//		你好/vl 蠢/a
		String string = ConnectUtil.get(url+str).trim();
		String[] split = string.split(" ");
		for(String st:split){
			int indexOf = st.indexOf("/");
			String var = st.substring(0, indexOf);
			String type = st.substring(indexOf, st.length());
			saveMap(varNum,var);
			saveMap(typeNum,type);
			saveMap(strNum,st);
		}
	}
	
	public static void saveMap(Map<String,Integer> map,String str){
		Integer integer = map.get(str)==null?1:(map.get(str)+1);
		map.put(str, integer);
	}
	
	public static void toprint(Map<String,Integer> map){
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for(Entry<String, Integer> ent:entrySet){
			System.out.println("var="+ent.getKey()+";num="+ent.getValue());
		}
	}
}
