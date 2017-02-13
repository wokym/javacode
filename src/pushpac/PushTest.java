package pushpac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PushTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String file="C:\\kym\\work\\eclipse\\MyEclipse_kym_k\\excelJob\\src\\pushpac\\dan.txt";
		try(FileInputStream ft=new FileInputStream(new File(file))){
			BufferedReader br = new BufferedReader(new InputStreamReader(ft));
			String s = "";
			Map<Integer,Integer> map=new HashMap<Integer,Integer>();
			for(int i=1;i<1000;i++){
				map.put(i, 0);
			}
			while ((s = br.readLine()) != null) {
				int parseInt = Integer.parseInt(s.trim());
				int ik=map.get(parseInt);
				ik++;
				map.put(parseInt, ik);
			}
			Set<Entry<Integer, Integer>> entrySet = map.entrySet();
			int code1=0,code2=0,code3=0;
			for(Entry<Integer, Integer> en:entrySet){
				int key = en.getKey();
				int value = en.getValue();
				if(value==1){
					code1++;
				}
				if(value==2){
					code2++;
				}
				if(value==3){
					code3++;
				}
			}
			System.out.println(code1+"==="+code2+"===="+code3);
		}
	}
}
