package test;

public enum EnumClass {
	M300(null){
		@Override
		public void getStr() {
			System.out.println("ooollp");
		}
	},
	M400(new InterfaceImpl());
	
	private  EInterface  facEInterface;
	private EnumClass(EInterface str){
		this.facEInterface=str;
	}
	public void getStr(){
		facEInterface.getStr("haha");
	}
}

