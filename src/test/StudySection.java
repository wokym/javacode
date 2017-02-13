package test;

public enum StudySection {
	PrimarySchool(10,"小学"){
	}
	,JuniorHighSchool(20,"初中"){
		
	}
	,HighSchool(30,"高中"){
		
	};
	private int type;
	private String name;
	private  StudySection(int ty,String name){
		type=ty;
		this.name=name;
	}
	public int type(){
		return type;
	}
	@Override
	public String toString() {
		return this.name;
	}
	public static StudySection getStudySectionBySectionID(int ty){
		for (StudySection ss: StudySection.values()){
			if(ss.type==ty)return ss;
		}
		return null;
	}
	
}
