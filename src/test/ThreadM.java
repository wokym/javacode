package test;
import java.util.*;
import java.util.Map.Entry;
public class ThreadM {
	public static void main(String[] args) throws Exception {
//		List<String> a=new ArrayList();
//		AA aa=new AA("AAAAA");
//		aa.list=a;
//		AA bb=new AA("bbbbb");
//		bb.list=a;
//		AA cc=new AA("ccccc");
//		cc.list=a;
//		AA dd=new AA("ddddd");
//		dd.list=a;
//		aa.start();bb.start();
//		Thread.sleep(1000);
//		cc.start();dd.start();
//		Map<Integer,String> map =new HashMap<Integer, String>();
//		map.put(1, null);
//		AA aa=new AA(map);
//		aa.start();
//		Thread.sleep(10);
//		System.out.println("11111111111111"+map.remove(1));
		final Map<Integer,Map<Integer,String>> map=new HashMap();
		   map.put(1, new HashMap<Integer,String>());
		   Map<Integer, String> map2 = map.get(1);
		   Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {

					map.remove(1);
			System.out.println("llll");
				}
		   });
		   thread.start();
		   Thread.sleep(1000);
		   map2.put(10, "haha");
		   System.out.println(map2);
		   System.out.println(map.get(1));
	}
}

class AA extends Thread{
	public List<String> list;
	public Map<Integer,String> map;
	public String name;
	public AA(String name){
		this.name=name;
	}
	public AA(Map<Integer,String> map){
		this.map=map;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		for(int i=0;i<100;i++){
//			list.add("name============"+name+"***"+i);
//			System.out.println("name============"+name+"***"+i);if(answer.containsKey(class_id)&&null==answer.get(class_id)){
			
			Set<Entry<Integer, String>> entrySet = map.entrySet();
			
//			for(Entry<Integer, String> entry:entrySet){
//				System.out.println(entrySet.size()+"_____________"+map.size());
//				if(entry.getKey()==1){
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println(entrySet.size()+"____++++++_____"+map.size());
//					System.out.println("********"+map.containsKey(1));
//					System.out.println(entry);
//					entry.setValue("kkkl");
//					System.out.println(entry);
//				}
//			}
			System.out.println("============"+map.get(1));
//		}
//		}
	}
}