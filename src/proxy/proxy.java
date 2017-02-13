package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class proxy {
	//char short long float double String BOOLEAN BYTE
		private static final List<Class> clas=new ArrayList<>();
		static{
			clas.add(byte.class);
			clas.add(Byte.class);
			clas.add(int.class);
			clas.add(Integer.class);
			clas.add(char.class);
			clas.add(Character.class);
			clas.add(long.class);
			clas.add(Long.class);
			clas.add(short.class);
			clas.add(Short.class);
			clas.add(double.class);
			clas.add(Double.class);
			clas.add(float.class);
			clas.add(Float.class);
			clas.add(boolean.class);
			clas.add(Boolean.class);
			clas.add(String.class);
		}
		
	public static void main(String[] args) {
		final A a=new A();
		InterfaceA newProxyInstance = (InterfaceA)Proxy.newProxyInstance(A.class.getClassLoader(), A.class.getInterfaces(), new InvocationHandler(){
		@Override
		public Object invoke(Object proxy1, Method method, Object[] args)
				throws Throwable {
	        	Object result=null;
	        	System.out.println("start ....");
	        	//proxy为代理对象，如果使用将重复调用该方法，代理对象所有方法都会走invoke方法
	            result = method.invoke(a, args);//如果用到了同类中，已经加注解的方法，则该方法注解不可用，因为此时对象用的原对象而非使用注解的代理对象！
//	            channelParseData2.$invoke("getParseResult", new String[]{"com.turing.base.platfrom.model.InputMessage"}, new Object[]{parseObject2});
	           System.out.println("method===="+method.getName());
	           System.out.println(proxy.class.getName());
	           
	           String [] strs=new String[args.length];
	           Object [] objs=new Object[args.length];
	           for(int i=0;i<args.length;i++){
	        	   Object object = args[i];
	        	   Class<? extends Object> class1 = object.getClass();
	        	// 基本类型以及Date,List,Map等不需要转换，直接调用 
	        	   boolean primitive = class1.isPrimitive()||class1.equals(String.class)||class1.equals(Date.class)
	        			   ||class1.equals(List.class)||class1.equals(Map.class);
	        	   strs[i]=class1.getName();
	        	   if(primitive){
	        		   objs[i]=object;
	        	   }else{
	        		   if(class1.isArray()){
	        			   objs[i]= Arrays.toString((Object [])object);
	        		   }else
	        		   objs[i]=JSONObject.fromObject(object).toString();
	        	   }
	           }
	           System.out.println("1111===="+Arrays.toString(strs));
	           System.out.println("2222===="+Arrays.toString(objs));
	           
	            System.out.println(proxy1==a);
	            System.out.println("end ....");
	            
	            Class<?> returnType = method.getReturnType();
	            //基本类型
	            //集合
	            //
	            return result;
		}});
		newProxyInstance.soos(1,new String[]{"123"});
	}
}
class A implements InterfaceA{
	public void so(){
		System.out.println("AAA");
	}

	@Override
	public void soos(int gt, String[] strs) {
		// TODO Auto-generated method stub
		
	}
}
