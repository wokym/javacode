package test;

public class TestStatic {
	public static void main(String[] args) {
//		Super c=new C();
//		c.soWo();
//		c.soso();
//		System.out.println("-------------------");
//		C cc=(C)c;
//		cc.soWo();
//		cc.soso();
		long bs=46530560;
		long all=46532798;
		System.out.println(bs*100/all);
	}
}
class Super{
	static int A=10;
	int B=20;
	static void soWo(){
		System.out.println("p");
	}
	void soso(){
		
	}
}
//静态方法不能被子类覆盖，具体调用对象由声明的变量类型决定（class文件编译时与类名绑定），属性优先调用自身属性
class C extends Super{
	static int A=1;
	int B=2;
	static void soWo(){
		System.out.println(A);
		System.out.println("c");
	}
	@Override
	void soso(){
		System.out.println(A);
	}
}