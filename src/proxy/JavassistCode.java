package proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class JavassistCode {
	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass ctClass = pool.get("proxy.Jaa");
		ctClass.setSuperclass(pool.get("proxy.JA"));
		/**
		 * 这里无法用new的形式来创建一个对象，因为已经classloader中不能有两个相同的对象,否则会报异常如下：
		 *Caused by: java.lang.LinkageError: loader (instance of  sun/misc/Launcher$AppClassLoader):
		 *attempted  duplicate class definition for name: "Point"
		 * 也不能让虚拟机加载该对象Class.forName("proxy.Jaa")
		 **/
//		Class<?> forName = Class.forName("proxy.Jaa");
//		Jaa ja=new Jaa();
//		ja.seeJa();
//		System.out.println(forName);
		ctClass.writeFile();
		Class aClass = ctClass.toClass();
		JA kkk = (JA)aClass.newInstance();
		kkk.seeJA();
		System.out.println(kkk.getClass());
	}
}
class Jaa{
	public void seeJa(){
		System.out.println("seeAA.....Jaa");
	}
}
class JA{
	public void seeJA(){
		System.out.println("seeaa.....JA");
	}
}