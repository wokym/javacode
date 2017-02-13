package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualsClass {
	public static void main(String[] args) {
//		List<Equals> ens=new ArrayList<>();
//		ens.add(new Equals(1));
//		ens.contains(new Equals(2));
		Map map=new HashMap<>();
		map.put(new Equals(1), 1);
		map.put(new Equals(2), 1);
	}
}
class Equals{
	private int id;
	public Equals(int id){
		this.id=id;
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals======"+id);
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		System.out.println("hashCode======"+id);
		return 1;
	}
}