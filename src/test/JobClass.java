package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobClass {
	public static void main(String[] args) {
//		Integer a=new Integer(1);
//		Integer b=new Integer(2);
//		List<Integer> ss=new ArrayList<>();
//		ss.add(b);
//		ss.add(a);
//		System.out.println(ss.contains(1));
//		Map map=new HashMap<>();
//		map.put(1, null);
//		System.out.println(map.containsKey(2));
//		System.out.println(map.get(1)==map.get(2));
//		map.remove(1);
//		System.out.println(map.containsKey(1));
		String ssss="D:\\kym\\myeclipse\\dev\\cct\\interacteducation";
		String str=".*[/[\\\\]][0-9]+_[0-9]+\\.png";
		String sss="×Üasa/dasd\\123_99.png";
		System.out.println(sss.matches(str));
	}
}
