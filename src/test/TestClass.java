package test;
/**
 * set  添加 （调用hashcode，决定存放位置）先比较对象hashcode值 相同，继续比较equals
 * list 去重 直接调用equals 方法比较
 * */
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestClass {
	public static void main(String[] args) throws Exception {
			List<A> list=new ArrayList<A>();
			A a=new A();
			list.add(a);
			list.add(a);
			System.out.println(list.size());
			System.out.println(list.contains(new A()));
//			HashSet<A> set=new HashSet<>();
//			set.add(new A());
//			set.add(new A());
//			System.out.println(set.size());
//			System.out.println(set.contains(new A()));
//			SimpleDateFormat sp=new SimpleDateFormat("MM");
//			Date asfd = sp.parse("1990/09/13");
//			Date asfds = sp.parse("1990/09/13");
//			List<Date> ldate=new ArrayList<Date>();
//			ldate.add(asfds);
//			System.out.println(ldate.contains(asfd));
//			System.out.println(sp.format(asfds));
//			Date date = new Date();
//			System.out.println(asfds.compareTo(date));
//			int indexOf = "213.23".indexOf(".");
//			String[] split = "213.23".split("\\.");
//			System.out.println(split[0]);
//			System.out.println(sp.format(date));
//			
//			String start=" 9-15-00";
//			String end=" 18-00-00";
//			SimpleDateFormat datasim=new SimpleDateFormat("yyy-MM-dd");
//			SimpleDateFormat fuz=new SimpleDateFormat("yyy-MM-dd HH-mm-ss");
//			String format = datasim.format(date);
//			Date parse = fuz.parse(format+start);
//			long longs=date.getTime()-parse.getTime();
//			System.out.println(longs/1000/60/60+"==="+longs/1000/60%60);
//			
//			 Calendar cal = Calendar.getInstance();
//			  cal.setTime(datasim.parse("2015-05-31"));
//			   System.out.println("asd"+cal.get(Calendar.DAY_OF_WEEK));
//		link();
//		EnumClass valueOf = EnumClass.valueOf("M300");
//		valueOf.getStr();
//		EnumClass valueOfw = EnumClass.valueOf("M400");
//		valueOfw.getStr();
//		EnumClass valueOfw1 = EnumClass.valueOf("M400");
//		valueOfw1.getStr();
//		valueOfw.getStr();
//		String systemRoles="4,y=asd";
//		int binarySearch = Arrays.binarySearch(systemRoles.split("=")[0].split(","), "4");
//		boolean fa=Arrays.binarySearch(systemRoles.split("=")[0].split(","), "4")<0;
//		System.out.println(fa);
//		Integer aa=null;
//		if(aa==1)
//			System.out.println();
	}
	public static void link(){
		LinkedList<String> a=new LinkedList<>();
		a.add("aaaa");
		a.add("bbbbb");
		a.add("ccccc");
		String pop = a.pop();
		System.out.println();
		String poll = a.poll();
		System.out.println();
		a.push("asdsd");
		System.out.println();
	}
}
class A{
	@Override
	public int hashCode() {
		System.out.println("hsahCode");
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("asd");
		return super.equals(obj);
	}
	public A(int id){}
	public A(){}
}
