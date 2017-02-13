package test;


public class EnumTest{
	public static void main(String[] args) {
//		Message.strInfo1
		Message[] values = Message.values();
		System.out.println(Message.valueOf("q"+2).strInfo1);
		
	}
}

enum Message {
	
	q1("M300Info1","M300Info2"),q2("M400Info1","M400Info2"),q0("M400Info1","M400Info2");
	
	public String strInfo1=null;
	public String strInfo2=null;
	private Message(String strInfo1,String strInfo2){
		this.strInfo1=strInfo1;
		this.strInfo2=strInfo2;
	}
	
}
