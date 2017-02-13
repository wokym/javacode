package parentInfo;

public abstract class ParentC {
	public abstract void toStart();
	public abstract void toParse();
	public String getInfo(){
		 AppInfo annotation = this.getClass().getAnnotation(AppInfo.class);
		return annotation.value();
	}
}
