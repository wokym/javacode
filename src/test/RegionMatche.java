package test;

public class RegionMatche {
	public static void main(String[] args) {
		region();
	}
	
	public static  void region() {
	    String str1 = "Collection of tutorials";
	    String str2 = "Consists of different tutorials";

	    /* matches characters from index 14 in str1 to characters from
	    index 22 in str2 considering same case of the letters */
	    boolean match1 = str1.regionMatches(14, str2, 22, 9);
	    System.out.println("region matched = " + match1);
	    
	    /* considering different case, "true" is set which will ignore
	    case when matched */
	    str2 = "Consists of different Tutorials";
	    match1 = str1.regionMatches(true, 14, str2, 22, 9); 
	    System.out.println("region matched = " + match1);   

	}
}
