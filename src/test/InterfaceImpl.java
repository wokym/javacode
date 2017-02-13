package test;

public class InterfaceImpl implements EInterface {
	public InterfaceImpl(){
		System.out.println("000000000000000000000000000000");
	}

	@Override
	public void getStr(String str) {
		System.out.println(str);
	}

}
