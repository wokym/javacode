package proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class JavassistCode {
	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass ctClass = pool.get("test.Rectangle");
		ctClass.setSuperclass(pool.get("test.Point"));
		ctClass.writeFile();
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