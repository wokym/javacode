package parentInfo;
@AppInfo("Children")
public class Children extends ParentC{

	@Override
	public void toStart() {
		System.out.println(getInfo()+".toStart");
	}

	@Override
	public void toParse() {
		System.out.println(getInfo()+".toParse");
	}
	
	public static void main(String[] args) {
		ParentC ch=new Children();
		ParentC ch2=new ChildrenII();
		ch.toStart();ch2.toStart();
	}
}
@AppInfo("ChildrenII")
class ChildrenII extends ParentC{

	@Override
	public void toStart() {
		System.out.println(getInfo()+".toStart");
		
	}

	@Override
	public void toParse() {
		System.out.println(getInfo()+".toParse");
		
	}
	
}

